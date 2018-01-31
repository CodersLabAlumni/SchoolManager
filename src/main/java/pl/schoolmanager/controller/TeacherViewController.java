package pl.schoolmanager.controller;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.Mark;
import pl.schoolmanager.entity.Schedule;
import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.repository.DivisionRepository;
import pl.schoolmanager.repository.MarkRepository;
import pl.schoolmanager.repository.ScheduleRepository;
import pl.schoolmanager.repository.StudentRepository;
import pl.schoolmanager.repository.SubjectRepository;
import pl.schoolmanager.repository.TeacherRepository;

@Controller
@RequestMapping("/teacherView")
public class TeacherViewController {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private DivisionRepository divisionRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;

	@GetMapping("/all")
	public String all(Model m) {
		return "test";
	}

	// HOME
	@GetMapping("")
	public String teacherHome(Model m) {
		return "teacher_view/teacher_subjects";
	}

	// SUBJECTS
	@GetMapping("/subjects")
	public String teacherSubjects(Model m) {
		return "teacher_view/teacher_subjects";
	}

	@GetMapping("/createSubjects")
	public String createSubject(Model m) {
		m.addAttribute("subject", new Subject());
		return "teacher_view/new_subject";
	}

	@PostMapping("/createSubjects")
	@Transactional
	public String createSubjectPost(@Valid @ModelAttribute Subject subject, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "teacher_view/new_subject";
		}
		HttpSession s = SessionManager.session();
		Teacher teacher = (Teacher) s.getAttribute("thisTeacher");
		School school = (School) s.getAttribute("thisSchool");

		teacher.getSubject().add(subject);
		teacherRepository.save(teacher);

		subject.getTeacher().add(teacherRepository.findOne(teacher.getId()));
		subject.setSchool(school);
		subjectRepository.save(subject);
		return "redirect:/teacherView/subjects";
	}

	@GetMapping("/viewSubject")
	public String viewSessionSubject() {
		return "teacher_view/show_subject";
	}

	// READ
	@GetMapping("/viewSubject/{subjectId}")
	public String viewSubject(Model m, @PathVariable long subjectId) {
		HttpSession s = SessionManager.session();
		Subject subject = this.subjectRepository.findOne(subjectId);
		s.setAttribute("subject", subject);
		return "teacher_view/show_subject";
	}
	
	// UPDATE
	@GetMapping("/updateSubject/{subjectId}")
	public String updateSubject(Model m, @PathVariable long subjectId) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("subject", subject);
		return "teacher_view/edit_subject";
	}

	@PostMapping("/updateSubject/{subjectId}")
	public String updateSubjectPost(@Valid @ModelAttribute Subject subject, BindingResult bindingResult,
			@PathVariable long subjectId) {
		if (bindingResult.hasErrors()) {
			return "teacher_view/edit_subject";
		}
		subject.setId(subjectId);
		this.subjectRepository.save(subject);
		return "index";
	}

	// DELETE
	@GetMapping("/deleteSubject/{subjectId}")
	public String deleteSubject(@PathVariable long subjectId) {
		this.subjectRepository.delete(subjectId);
		return "index";
	}

	// SHOW SUDENTS IN DIVISION
	@GetMapping("/showDivision/{divisionId}")
	public String showDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		List<Student> divisionStudents = this.studentRepository.findAllByDivisionId(divisionId);
		m.addAttribute("students", divisionStudents);
		m.addAttribute("division", division);
		return "teacher_view/allStudents_division";
	}

	// STUDENT MARKS MANAGEMENT

	// CREATE FOR
	@GetMapping("/createMark/{studentId}")
	public String createMark(@PathVariable long studentId, Model m) {
		/*
		 * HttpSession s = SessionManager.session(); Subject thisSubject = (Subject)
		 * s.getAttribute("subject"); Student thisStudent =
		 * this.studentRepository.findOne(studentId);
		 */
		Mark mark = new Mark();
		/*
		 * mark.setSubject(thisSubject); mark.setStudent(thisStudent);
		 */
		m.addAttribute("mark", mark);
		return "teacher_view/new_mark";
	}

	@PostMapping("/createMark/{studentId}")
	public String createMarkPost(@Valid @ModelAttribute Mark mark, BindingResult bindingResult,
			@PathVariable long studentId, Model m) {
		if (bindingResult.hasErrors()) {
			return "mark/new_mark";
		}
		HttpSession s = SessionManager.session();
		Subject thisSubject = (Subject) s.getAttribute("subject");
		Student thisStudent = this.studentRepository.findOne(studentId);
		mark.setSubject(thisSubject);
		mark.setStudent(thisStudent);
		this.markRepository.save(mark);
		return "redirect:/teacherView/";
	}

	// READ
	@GetMapping("/viewMark/{markId}")
	public String viewMark(Model m, @PathVariable long markId) {
		Mark mark = this.markRepository.findOne(markId);
		m.addAttribute("mark", mark);
		return "mark/show_mark";
	}

	// UPDATE
	@GetMapping("/updateMark/{markId}")
	public String updateMark(@RequestParam long subject, @RequestParam long student, Model m,
			@PathVariable long markId) {
		Mark mark = this.markRepository.findOne(markId);
		m.addAttribute("mark", mark);
		return "mark/edit_mark";
	}

	@PostMapping("/updateMark/{markId}")
	public String updateMarkPost(@Valid @ModelAttribute Mark mark, BindingResult bindingResult,
			@PathVariable long markId) {
		if (bindingResult.hasErrors()) {
			return "mark/edit_mark";
		}
		mark.setId(markId);

		this.markRepository.save(mark);
		Long divisionId = mark.getStudent().getDivision().getId();
		Long subjectId = mark.getSubject().getId();
		return "redirect:/division/inside/marks/" + divisionId + "/" + subjectId;
	}

	// DELETE
	@DeleteMapping("/deleteMark/{markId}")
	public String deleteMark(@PathVariable long markId) {
		this.markRepository.delete(markId);
		return "index";
	}

	// SHOW ALL
	@ModelAttribute("availableMarks")
	public List<Mark> getMarks() {
		return this.markRepository.findAll();
	}
	
	@GetMapping("/addSubject/{divisionId}/{dayId}/{timeId}")
	public String addSubject(@PathVariable long divisionId, @PathVariable int dayId, @PathVariable int timeId) {
		DayOfWeek dayOfWeek = DayOfWeek.of(dayId);
		Division division = this.divisionRepository.findOne(divisionId);
		Schedule schedule = scheduleRepository.findOneByDivisionAndDay(division, dayOfWeek);
		if (schedule == null) {
			schedule = new Schedule();
		}
		schedule.setDay(dayOfWeek);
		schedule.setDivision(division);
		HttpSession s = SessionManager.session();
		Subject subject = (Subject) s.getAttribute("subject");
		Map<Integer, Subject> daySubject = schedule.getDaySubject();
		daySubject.put(timeId, subject);
		schedule.setDaySubject(daySubject);
		scheduleRepository.save(schedule);
		return "redirect:/teacherView/viewSubject";
	}
	
	@GetMapping("/removeSubject/{divisionId}/{dayId}/{timeId}")
	public String removeSubject(@PathVariable long divisionId, @PathVariable int dayId, @PathVariable int timeId) {
		DayOfWeek dayOfWeek = DayOfWeek.of(dayId);
		Division division = this.divisionRepository.findOne(divisionId);
		Schedule schedule = scheduleRepository.findOneByDivisionAndDay(division, dayOfWeek);
		if (schedule == null) {
			schedule = new Schedule();
		}
		schedule.setDay(dayOfWeek);
		schedule.setDivision(division);
		Map<Integer, Subject> daySubject = schedule.getDaySubject();
		daySubject.remove(timeId);
		schedule.setDaySubject(daySubject);
		scheduleRepository.save(schedule);
		return "redirect:/teacherView/viewSubject";
	}

	@ModelAttribute("teacherSubjects")
	public List<Subject> getSubjects() {
		HttpSession s = SessionManager.session();
		Teacher teacher = (Teacher) s.getAttribute("thisTeacher");
		return this.subjectRepository.findAllByTeacher(teacher);
	}
	
	@ModelAttribute("mondaySubjects")
	public List<String> getMondaySubject() {
		Schedule schedule = this.scheduleRepository.findOneByDay(DayOfWeek.MONDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
	@ModelAttribute("tuesdaySubjects")
	public List<String> getTuesdaySubject() {
		Schedule schedule = this.scheduleRepository.findOneByDay(DayOfWeek.TUESDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
	@ModelAttribute("wednesdaySubjects")
	public List<String> getWednesdaySubject() {
		Schedule schedule = this.scheduleRepository.findOneByDay(DayOfWeek.WEDNESDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
	@ModelAttribute("thursdaySubjects")
	public List<String> getThursdaySubject() {
		Schedule schedule = this.scheduleRepository.findOneByDay(DayOfWeek.THURSDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
	@ModelAttribute("fridaySubjects")
	public List<String> getFridaySubject() {
		Schedule schedule = this.scheduleRepository.findOneByDay(DayOfWeek.FRIDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	

}

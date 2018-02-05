package pl.schoolmanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.Lesson;
import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.SchoolAdmin;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.DivisionRepository;
import pl.schoolmanager.repository.LessonRepository;
import pl.schoolmanager.repository.SchoolAdminRepository;
import pl.schoolmanager.repository.StudentRepository;
import pl.schoolmanager.repository.SubjectRepository;
import pl.schoolmanager.repository.UserRoleRepository;

@Controller
@RequestMapping("/schoolAdminView")
@Transactional
public class SchoolAdminViewController {
	
	@Autowired
	private SchoolAdminRepository schoolAdminRepo;
	@Autowired
	private DivisionRepository divisionRepo;
	@Autowired
	private SubjectRepository subjectRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private UserRoleRepository userRoleRepo;
	@Autowired
	LessonRepository lessonRepo;

	@GetMapping("/{schooladminId}/access")
	public String schoolAdminHome(@PathVariable long schooladminId, Model m) {
		SchoolAdmin schoolAdmin = schoolAdminRepo.findOne(schooladminId);
		Hibernate.initialize(schoolAdmin.getSchool().getDivision());
		Hibernate.initialize(schoolAdmin.getSchool().getTeacher());
		Hibernate.initialize(schoolAdmin.getSchool().getStudent());
		Hibernate.initialize(schoolAdmin.getSchool().getSubject());
		m.addAttribute("schoolAdmin", schoolAdmin);
		return "school_admin_view/school";
	}
	
	@GetMapping("/{schooladminId}/createDivision")
	public String createDivisionGet(@PathVariable long schooladminId, Model m) {
		m.addAttribute("division", new Division());
		return "school_admin_view/create_division";
	}
	
	@PostMapping("/{schooladminId}/createDivision")
	public String createDivisionPost(@PathVariable long schooladminId, Model m, 
			@Valid @ModelAttribute Division division, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "school_admin_view/create_division";
		}
		School school = this.schoolAdminRepo.findOne(schooladminId).getSchool();
		division.setSchool(school);
		this.divisionRepo.save(division);
		return "redirect:/schoolAdminView/access/{schooladminId}";
	}
	
	@GetMapping("/{schooladminId}/updateDivision/{divisionId}")
	public String updateDivisionGet(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepo.findOne(divisionId);
		m.addAttribute("division", division);
		return "school_admin_view/create_division";
	}
	
	@PostMapping("/{schooladminId}/updateDivision/{divisionId}")
	public String updateDivisionPost(Model m, @PathVariable long schooladminId, @PathVariable long divisionId,
			@ModelAttribute Division division, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "school_admin_view/create_division";
		}
		School school = this.schoolAdminRepo.findOne(schooladminId).getSchool();
		division.setSchool(school);
		division.setId(divisionId);
		this.divisionRepo.save(division);
		return "redirect:/schoolAdminView/access/{schooladminId}";
	}
	
	@GetMapping("/{schooladminId}/createSubject")
	public String createSubjectGet(@PathVariable long schooladminId, Model m) {
		m.addAttribute("subject", new Subject());
		return "school_admin_view/create_subject";
	}
	
	@PostMapping("/{schooladminId}/createSubject")
	public String createSubjectPost(@PathVariable long schooladminId, Model m, 
			@Valid @ModelAttribute Subject subject, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "school_admin_view/create_subject";
		}
		School school = this.schoolAdminRepo.findOne(schooladminId).getSchool();
		subject.setSchool(school);
		this.subjectRepo.save(subject);
		return "redirect:/schoolAdminView/access/{schooladminId}";
	}
	
	
	@GetMapping("/{schooladminId}/updateSubject/{subjectId}")
	public String updateSubjectGet(Model m, @PathVariable long subjectId) {
		Subject subject = this.subjectRepo.findOne(subjectId);
		m.addAttribute("subject", subject);
		return "school_admin_view/create_subject";
	}
	
	@PostMapping("/{schooladminId}/updateSubject/{subjectId}")
	public String updateSubjectPost(Model m, @PathVariable long schooladminId, @PathVariable long subjectId,
			@ModelAttribute Subject subject, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "school_admin_view/create_subject";
		}
		School school = this.schoolAdminRepo.findOne(schooladminId).getSchool();
		subject.setSchool(school);
		subject.setId(subjectId);
		this.subjectRepo.save(subject);
		return "redirect:/schoolAdminView/access/{schooladminId}";
	}
	
	
	
	@GetMapping("/{schooladminId}/addSubject/{divisionId}")
	public String addSubject(Model m, @PathVariable long schooladminId, @PathVariable long divisionId) {
		List<Subject> schoolSubjects = schoolAdminRepo.findOne(schooladminId).getSchool().getSubject();
		List<Subject> divisionSubjects = divisionRepo.findOne(divisionId).getSubject();
		List<Subject> subjectsNotInDivision = new ArrayList<>(schoolSubjects);
		SchoolAdmin schoolAdmin = this.schoolAdminRepo.findOne(schooladminId);
		subjectsNotInDivision.removeAll(divisionSubjects);
		Division division = this.divisionRepo.findOne(divisionId);
		m.addAttribute("schoolAdmin", schoolAdmin);
		m.addAttribute("division", division);
		m.addAttribute("subjects", divisionSubjects);
		m.addAttribute("subjectsNotInDivision", subjectsNotInDivision);
		return "school_admin_view/addSubject2division";
	}

	@GetMapping("/{schooladminId}/addSubject/{divisionId}/{subjectId}")
	public String addSubject(@PathVariable long schooladminId,  
			@PathVariable long divisionId,  @PathVariable long subjectId) {
		Division division = this.divisionRepo.findOne(divisionId);
		Subject subject = this.subjectRepo.findOne(subjectId);
		subject.getDivision().add(division);
		this.subjectRepo.save(subject);
		return "redirect:/schoolAdminView/{schooladminId}/addSubject/{divisionId}";
	}
	
	@GetMapping("/{schooladminId}/removeSubject/{divisionId}/{subjectId}")
	public String removeSubject(@PathVariable long schooladminId,  
			@PathVariable long divisionId,  @PathVariable long subjectId) {
		Division division = this.divisionRepo.findOne(divisionId);
		Subject subject = this.subjectRepo.findOne(subjectId);
		ListIterator<Division> div = subject.getDivision().listIterator();
		while (div.hasNext()) {
			if (div.next().getId() == division.getId()) {
				div.remove();
			}
		}
		this.subjectRepo.save(subject);
		return "redirect:/schoolAdminView/{schooladminId}/addSubject/{divisionId}";
	}
	
	@GetMapping("/{schooladminId}/addStudent/{divisionId}")
	public String addStudent(Model m, @PathVariable long schooladminId, @PathVariable long divisionId) {
		List<Student> schoolStudents = schoolAdminRepo.findOne(schooladminId).getSchool().getStudent();
		List<Student> divisionStudents = divisionRepo.findOne(divisionId).getStudent();
		List<Student> studentsNotInDivision = new ArrayList<>(schoolStudents);
		studentsNotInDivision.removeAll(divisionStudents);
		List<Student> tempList = new ArrayList<>();
		for (Student s : studentsNotInDivision) {
			if (s.getUserRole().isEnabled()) {
				tempList.add(s);
			}
		}
		studentsNotInDivision = tempList;
		SchoolAdmin schoolAdmin = this.schoolAdminRepo.findOne(schooladminId);
		Division division = this.divisionRepo.findOne(divisionId);
		m.addAttribute("schoolAdmin", schoolAdmin);
		m.addAttribute("division", division);
		m.addAttribute("students", divisionStudents);
		m.addAttribute("studentsNotInDivision", studentsNotInDivision);
		return "school_admin_view/addStudent2division";
	}
	
	@GetMapping("/{schooladminId}/addStudent/{divisionId}/{studentId}")
	public String addStudent(@PathVariable long schooladminId,  
			@PathVariable long divisionId,  @PathVariable long studentId) {
		Division division = this.divisionRepo.findOne(divisionId);
		Student student = this.studentRepo.findOne(studentId);
		student.getDivision().add(division);
		this.studentRepo.save(student);
		return "redirect:/schoolAdminView/{schooladminId}/addStudent/{divisionId}";
	}
	
	@GetMapping("/{schooladminId}/removeStudent/{divisionId}/{studentId}")
	public String removeStudent(@PathVariable long schooladminId,  
			@PathVariable long divisionId,  @PathVariable long studentId) {
		Division division = this.divisionRepo.findOne(divisionId);
		Student student = this.studentRepo.findOne(studentId);
		ListIterator<Division> div = student.getDivision().listIterator();
		while (div.hasNext()) {
			if (div.next().getId() == division.getId()) {
				div.remove();
			}
		}
		this.studentRepo.save(student);
		return "redirect:/schoolAdminView/{schooladminId}/addStudent/{divisionId}";
	}
	
	@GetMapping("/{schooladminId}/addLesson/{divisionId}")
	public String addLessonGet(Model m, @PathVariable long schooladminId, @PathVariable long divisionId) {
		List<Subject> subjects = this.divisionRepo.findOne(divisionId).getSubject();
		List<Teacher> teachers = this.schoolAdminRepo.findOne(schooladminId).getSchool().getTeacher();
		List<Teacher> tempList = new ArrayList<>();
		for (Teacher t : teachers) {
			if (t.getUserRole().isEnabled()) {
				tempList.add(t);
			}
		}
		teachers = tempList;
		List<Lesson> lessons = this.divisionRepo.findOne(divisionId).getLesson();
		SchoolAdmin schoolAdmin = this.schoolAdminRepo.findOne(schooladminId);
		Division division = this.divisionRepo.findOne(divisionId);
		Hibernate.initialize(schoolAdmin.getSchool().getTeacher());
		m.addAttribute("division", division);
		m.addAttribute("schoolAdmin", schoolAdmin);
		m.addAttribute("subjects", subjects);
		m.addAttribute("teachers", teachers);
		m.addAttribute("lessons", lessons);
		m.addAttribute("lesson", new Lesson());
		return "school_admin_view/addLesson2division";
	}
	
	@PostMapping("/{schooladminId}/addLesson/{divisionId}")
	public String addLessonPost(Model m, @PathVariable long schooladminId, @PathVariable long divisionId,
			@ModelAttribute Lesson lesson, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "school_admin_view/addLesson2division";
		}
		lesson.setDivision(this.divisionRepo.findOne(divisionId));
		this.lessonRepo.save(lesson);
		return "redirect:/schoolAdminView/{schooladminId}/addLesson/{divisionId}";
	}
	
	@GetMapping("/{schooladminId}/removeLesson/{divisionId}/{lessonId}")
	public String removeLesson(@PathVariable long schooladminId,  
			@PathVariable long divisionId,  @PathVariable long lessonId) {
		Lesson lesson = this.lessonRepo.findOne(lessonId);
		this.divisionRepo.findOne(divisionId).getLesson().remove(lesson);
		lesson.setDivision(null);
		this.lessonRepo.delete(lesson);
		return "redirect:/schoolAdminView/{schooladminId}/addLesson/{divisionId}";		
	}
	
	@GetMapping("/{schooladminId}/toggleEnabled/{userRoleId}")
	public String toogleEnabledUserRole(@PathVariable long schooladminId, @PathVariable long userRoleId) {
		UserRole userRole = this.userRoleRepo.findOne(userRoleId);
		if (userRole.isEnabled()) {
			userRole.setEnabled(false);
		} else {
			userRole.setEnabled(true);
		}
		this.userRoleRepo.save(userRole);
		return "redirect:/schoolAdminView/access/{schooladminId}";
	}
}
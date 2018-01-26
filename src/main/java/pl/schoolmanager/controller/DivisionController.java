package pl.schoolmanager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.Mark;
import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.DivisionRepository;
import pl.schoolmanager.repository.MarkRepository;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.StudentRepository;
import pl.schoolmanager.repository.SubjectRepository;
import pl.schoolmanager.repository.UserRepository;

@Controller
@RequestMapping("/division")
public class DivisionController {

	@Autowired
	private DivisionRepository divisionRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	@GetMapping("/all")
	public String all(Model m) {
		return "division/all_divisions";
	}

	@GetMapping("/create")
	public String createDivision(Model m) {
		m.addAttribute("division", new Division());
		return "division/new_division";
	}

	@PostMapping("/create")
	public String createDivisionPost(@Valid @ModelAttribute Division division, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "division/new_division";
		}
		this.divisionRepository.save(division);
		return "index";
	}

	@GetMapping("/view/{divisionId}")
	public String viewDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		m.addAttribute("division", division);
		return "division/show_division";
	}

	@GetMapping("/update/{divisionId}")
	public String updateDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		m.addAttribute("division", division);
		return "division/edit_division";
	}

	@PostMapping("/update/{divisionId}")
	public String updateDivisionPost(@Valid @ModelAttribute Division division, BindingResult bindingResult,
			@PathVariable long divisionId) {
		if (bindingResult.hasErrors()) {
			return "division/edit_division";
		}
		division.setId(divisionId);
		this.divisionRepository.save(division);
		return "index";
	}

	@GetMapping("/delete/{divisionId}")
	public String deleteDivision(@PathVariable long divisionId) {
		this.divisionRepository.delete(divisionId);
		return "index";
	}

	@GetMapping("/inside/{divisionId}")
	public String insideDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		List<Student> students = this.studentRepository.findAllByDivisionId(divisionId);
		List<Subject> subjects = this.subjectRepository.findAllByDivisionId(divisionId);
		m.addAttribute("division", division);
		m.addAttribute("students", students);
		m.addAttribute("subjects", subjects);
		return "division/inside_division";
	}

	@GetMapping("/addStudent/{divisionId}")
	public String addStudent(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		List<Student> students = this.studentRepository.findAllByDivisionId(divisionId);
		List<Student> studentsNotInDivision = this.studentRepository.findAllByDivisionIdIsNull();
		m.addAttribute("division", division);
		m.addAttribute("students", students);
		m.addAttribute("studentsNotInDivision", studentsNotInDivision);
		return "division/addStudent_division"; // view to be developed
	}

	@GetMapping("addStudent/{divisionId}/{studentId}")
	public String addStudent(@PathVariable long divisionId, @PathVariable long studentId) {
		Division division = this.divisionRepository.findOne(divisionId);
		Student student = this.studentRepository.findOne(studentId);
		student.setDivision(division);
		this.studentRepository.save(student);
		return "redirect:/division/addStudent/{divisionId}";
	}

	@GetMapping("/addSubject/{divisionId}")
	public String addSubject(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		List<Subject> subjects = this.subjectRepository.findAllByDivisionId(divisionId);
		List<Subject> subjectsNotInDivision = this.subjectRepository
				.findAllByDivisionIdIsNullOrDivisionIdIsNot(divisionId);
		m.addAttribute("division", division);
		m.addAttribute("subjects", subjects);
		m.addAttribute("subjectsNotInDivision", subjectsNotInDivision);
		return "division/addSubject_division";
	}

	@GetMapping("addSubject/{divisionId}/{subjectId}")
	public String addSubject(@PathVariable long divisionId, @PathVariable long subjectId) {
		Division division = this.divisionRepository.findOne(divisionId);
		Subject subject = this.subjectRepository.findOne(subjectId);
		subject.getDivision().add(division);
		this.subjectRepository.save(subject);
		return "redirect:/division/addSubject/{divisionId}";
	}

	@GetMapping("/inside/students/{divisionId}")
	public String studentsInsideDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		List<Student> students = this.studentRepository.findAllByDivisionId(divisionId);
		List<Subject> subjects = this.subjectRepository.findAllByDivisionId(divisionId);
		m.addAttribute("division", division);
		m.addAttribute("students", students);
		m.addAttribute("subjects", subjects);
		return "division/allStudents_division";
	}

	@GetMapping("/inside/subjects/{divisionId}")
	public String subjectsInsideDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		List<Subject> subjects = this.subjectRepository.findAllByDivisionId(divisionId);
		m.addAttribute("division", division);
		m.addAttribute("subjects", subjects);
		return "division/allSubjects_division";
	}

	@GetMapping("/inside/marks/{divisionId}/{subjectId}")
	public String subjectsMarksInsideDivision(Model m, @PathVariable long divisionId, @PathVariable long subjectId) {
		Division division = this.divisionRepository.findOne(divisionId);
		List<Mark> marks = this.markRepository.findAllBySubjectId(subjectId);
		List<Student> students = this.studentRepository.findAllByDivisionId(divisionId);
		List<Subject> subjects = this.subjectRepository.findAllByDivisionId(divisionId);
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("division", division);
		m.addAttribute("students", students);
		m.addAttribute("marks", marks);
		m.addAttribute("subjects", subjects);
		m.addAttribute("subject", subject);
		return "division/allStudentsMarks_division";
	}

	@ModelAttribute("availableDivisions")
	public List<Division> getSchoolDivisions() {
		HttpSession s = SessionManager.session();
		School school = (School) s.getAttribute("thisSchool");
		return this.divisionRepository.findAllBySchool(school);
	}
	
	// SHOW ALL DIVISIONS IN SCHOOL
	@ModelAttribute("availableDivisions")
	public List<Division> getDivisions() {
		return this.divisionRepository.findAll();
	}

	@ModelAttribute("countAllReceivedMessages")
	public Integer countAllReceivedMessages(Long receiverId) {
		return this.messageRepository.findAllByReceiverId(getLoggedUser().getId()).size();
	}

	@ModelAttribute("countAllSendedMessages")
	public Integer countAllSendedMessages(Long senderId) {
		return this.messageRepository.findAllBySenderId(getLoggedUser().getId()).size();
	}
	
	@ModelAttribute("countAllReceivedUnreadedMessages")
	public Integer countAllReceivedUnreadedMessages(Long receiverId, Integer checked) {
		return this.messageRepository.findAllByReceiverIdAndChecked(getLoggedUser().getId(), 0).size();
	}
	
	private User getLoggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
		return this.userRepository.findOneByUsername(username);
	}
}

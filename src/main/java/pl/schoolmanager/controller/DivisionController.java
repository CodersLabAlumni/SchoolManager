package pl.schoolmanager.controller;

import java.util.List;

import javax.validation.Valid;

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
import pl.schoolmanager.entity.Mark;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.repository.DivisionRepository;
import pl.schoolmanager.repository.MarkRepository;
import pl.schoolmanager.repository.StudentRepository;
import pl.schoolmanager.repository.SubjectRepository;

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


	@GetMapping("/all")
	public String all(Model m) {
		return "division/all_divisions";
	}

	// CREATE
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

	// READ
	@GetMapping("/view/{divisionId}")
	public String viewDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		m.addAttribute("division", division);
		return "division/show_division";
	}

	// UPDATE
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

	// DELETE
	@GetMapping("/delete/{divisionId}")
	public String deleteDivision(@PathVariable long divisionId) {
		this.divisionRepository.delete(divisionId);
		return "index";
	}

	// INSIDE DIVISION
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

	// ADD STUDENT TO DIVISION
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

	// ADD SUBJECT TO DIVISION
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

	// ALL STUDENTS IN DIVISION
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
	
	// ALL SUBJECTS IN DIVISION
	@GetMapping("/inside/subjects/{divisionId}")
	public String subjectsInsideDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		List<Subject> subjects = this.subjectRepository.findAllByDivisionId(divisionId);
		m.addAttribute("division", division);
		m.addAttribute("subjects", subjects);
		return "division/allSubjects_division";
	}

	// ALL MARKS IN SUBJECT IN DIVISION
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
	

	// SHOW ALL
	@ModelAttribute("availableDivisions")
	public List<Division> getDivisions() {
		return this.divisionRepository.findAll();
	}

}

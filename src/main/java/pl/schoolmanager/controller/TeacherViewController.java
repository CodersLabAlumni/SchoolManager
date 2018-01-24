package pl.schoolmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
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

import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.repository.DivisionRepository;
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
	
	@GetMapping("/all")
	public String all(Model m) {
		return "test";
//		return "teacher_view/division_students";
	}
	
	//SUBJECTS
	@GetMapping("/subjects")
	public String teacherSubjects(Model m) {
		return "teacher_view/teacher_subjects";
	}
	
	
	// CREATE
	@GetMapping("/createSubjects")
	public String createSubject(Model m) {
		m.addAttribute("subject", new Subject());
		return "teacher_view/new_subject";
	}

	@PostMapping("/createSubjects")
	public String createSubjectPost(@Valid @ModelAttribute Subject subject, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "teacher_view/new_subject";
		}
		HttpSession s = SessionManager.session();
		Teacher teacher = (Teacher) s.getAttribute("thisTeacher");
		School school = (School) s.getAttribute("thisSchool");
//		HttpSession s = SessionManager.session();
//		Teacher teacher = (Teacher) s.getAttribute("thisTeacher");
//		School school = (School) s.getAttribute("thisSchool");
//		List<Subject> subjects = teacher.getSubject();
//		subjects.add(subject);
//		teacher.setSubject(subjects);
//		m.addAttribute("teacher1", teacher);
//		subject.setSchool(school);
//		this.teacherRepository.save(teacher);
//		this.subjectRepository.save(subject);
		
/*		List<Subject> subjects = teacher.getSubject();
		subjects.add(subject);
		teacher.setSubject(subjects);
		this.teacherRepository.save(teacher);
		this.subjectRepository.save(subject);*/
//		teacher.setId(20l);
		m.addAttribute("teacher1", teacher);
		subject.setSchool(school);
		subject.getTeacher()/*.add(teacher)*/;
		List<Subject> subjects = teacher.getSubject();
		subjects.add(subject);
		teacher.setSubject(subjects);
		this.teacherRepository.save(teacher);
		this.subjectRepository.save(subject);
		return "test";
	}
	
	// READ
	@GetMapping("/viewSubject/{subjectId}")
	public String viewSubject(Model m, @PathVariable long subjectId) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("subject", subject);
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
	
	@ModelAttribute("teacherSubjects")
	public List<Subject> getSubjects() {
		HttpSession s = SessionManager.session();
		Teacher teacher = (Teacher) s.getAttribute("thisTeacher");
		return this.subjectRepository.findAllByTeacher(teacher);
	}
}

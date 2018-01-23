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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.repository.SubjectRepository;
import pl.schoolmanager.repository.TeacherRepository;

@Controller
@RequestMapping("/teacherView")
public class TeacherViewController {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
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
		List<Subject> subjects = teacher.getSubject();
		subjects.add(subject);
		teacher.setSubject(subjects);
		m.addAttribute("teacher1", teacher);
		this.subjectRepository.save(subject);
		this.teacherRepository.save(teacher);
		return "test";
	}
}

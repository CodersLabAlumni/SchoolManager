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
import pl.schoolmanager.entity.School;
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
	}
	
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
	public String createSubjectPost(@Valid @ModelAttribute Subject subject, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "teacher_view/new_subject";
		}
		HttpSession s = SessionManager.session();
		Teacher teacher = (Teacher) s.getAttribute("thisTeacher");
		School school = (School) s.getAttribute("thisSchool");

		subject.setSchool(school);
		subject.getTeacher().add(teacher);
		this.subjectRepository.save(subject);
		return "test";
	}
	
	@ModelAttribute("teacherSubjects")
	public List<Subject> getSubjects() {
		HttpSession s = SessionManager.session();
		Teacher teacher = (Teacher) s.getAttribute("thisTeacher");
		return this.subjectRepository.findAllByTeacher(teacher);
	}

}

package pl.schoolmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.Student;
import pl.schoolmanager.repository.StudentRepository;

@Controller
@RequestMapping("/studentView")
public class StudentViewController {

	@Autowired
	private StudentRepository studentRepository;
	

	@GetMapping("/all")
	public String all(Model m) {
		return "student_view/all_students";
	}

	// SHOW ALL
	@ModelAttribute("availableStudents")
	public List<Student> getStudents() {
		return this.studentRepository.findAll();
	}
}

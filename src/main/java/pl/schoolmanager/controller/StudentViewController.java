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
	

	@GetMapping("/division")
	public String all(Model m) {
		return "student_view/division_students";
	}
	
	@GetMapping("/subjects")
	public String subject(Model m) {
		return "student_view/subjects_student";
	}
	
	@GetMapping("/marks")
	public String marks(Model m) {
		return "student_view/marks_student";
	}


}

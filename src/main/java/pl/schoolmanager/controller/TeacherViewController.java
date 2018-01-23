package pl.schoolmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacherView")
public class TeacherViewController {

	@GetMapping("/all")
	public String all(Model m) {
		return "test";
//		return "teacher_view/division_students";
	}
}

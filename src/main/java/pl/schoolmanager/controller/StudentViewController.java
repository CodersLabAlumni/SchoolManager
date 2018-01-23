package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.repository.StudentRepository;

@Controller
@RequestMapping("/studentView")
public class StudentViewController {

	@Autowired
	private StudentRepository studentRepository;
	
	
}

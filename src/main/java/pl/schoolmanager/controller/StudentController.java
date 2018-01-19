package pl.schoolmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.Student;
import pl.schoolmanager.repository.StudentRepository;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	// CREATE
	@GetMapping("/create")
	public String createStudent(Model m) {
		m.addAttribute("student", new Student());
		return "student/new_student"; // view to be developed
	}

	@PostMapping("/create")
	public String createStudentPost(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "student/new_student"; // view to be developed
		}
		this.studentRepository.save(student);
		return "index"; // to decide where to return
	}

	// READ
	@GetMapping("/view/{studentId}")
	public String viewStudent(Model m, @PathVariable long studentId) {
		Student student = this.studentRepository.findOne(studentId);
		m.addAttribute("student", student);
		return "student/show_student"; // view to be developed
	}

	// UPDATE
	@GetMapping("/update/{studentId}")
	public String updateStudent(Model m, @PathVariable long studentId) {
		Student student = this.studentRepository.findOne(studentId);
		m.addAttribute("student", student);
		return "student/edit_student"; // view to be developed
	}

	@PostMapping("/update/{studentId}")
	public String updateStudentPost(@Valid @ModelAttribute Student student, BindingResult bindingResult,
			@PathVariable long studentId) {
		if (bindingResult.hasErrors()) {
			return "student/edit_student"; // view to be developed
		}
		student.setId(studentId);
		this.studentRepository.save(student);
		return "index"; // to decide where to return
	}

	// DELETE
	@GetMapping("/delete/{studentId}")
	public String deleteStudent(@PathVariable long studentId) {
		this.studentRepository.delete(studentId);
		return "index"; // to decide where to return
	}

	// SHOW ALL
	@ModelAttribute("availableStudents")
	public List<Student> getStudents() {
		return this.studentRepository.findAll();
	}
	
	@GetMapping("/all")
	public String all(Model m) {
		return "student/all_students";
	}
	
}

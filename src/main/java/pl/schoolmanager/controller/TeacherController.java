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

import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.repository.SubjectRepository;
import pl.schoolmanager.repository.TeacherRepository;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;
	

	@GetMapping("/all")
	public String all(Model m) {
		return "teacher/all_teachers";
	}
	
	// CREATE
	@GetMapping("/create")
	public String createTeacher(Model m) {
		m.addAttribute("teacher", new Teacher());
		return "teacher/new_teacher"; // view to be developed
	}

	@PostMapping("/create")
	public String createTeacherPost(@Valid @ModelAttribute Teacher teacher, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "teacher/new_teacher"; // view to be developed
		}
		this.teacherRepository.save(teacher);
		return "index"; // to decide where to return
	}

	// READ
	@GetMapping("/view/{teacherId}")
	public String viewTeacher(Model m, @PathVariable long teacherId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		m.addAttribute("teacher", teacher);
		return "teacher/show_teacher"; // view to be developed
	}

	// UPDATE
	@GetMapping("/update/{teacherId}")
	public String updateTeacher(Model m, @PathVariable long teacherId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		m.addAttribute("teacher", teacher);
		return "teacher/edit_teacher"; // view to be developed
	}

	@PostMapping("/update/{teacherId}")
	public String updateTeacherPost(@Valid @ModelAttribute Teacher teacher, BindingResult bindingResult,
			@PathVariable long teacherId) {
		if (bindingResult.hasErrors()) {
			return "teacher/edit_teacher"; // view to be developed
		}
		teacher.setId(teacherId);
		this.teacherRepository.save(teacher);
		return "index"; // to decide where to return
	}

	// DELETE
	@GetMapping("/delete/{teacherId}")
	public String deleteTeacher(@PathVariable long teacherId) {
		this.teacherRepository.delete(teacherId);
		return "index"; // to decide where to return
	}

	// SHOW ALL
	@ModelAttribute("availableTeachers")
	public List<Teacher> getTeachers() {
		return this.teacherRepository.findAll();
	}
	
	//ADD SUBJECT TO TEACHER
	@GetMapping("/addSubject/{teacherId}")
	public String addSubject(Model m, @PathVariable long teacherId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		List <Subject> subjects = this.subjectRepository.findAllByTeacherId(teacherId);
		List <Subject> subjectsNotTaught = this.subjectRepository.findAllByTeacherIdIsNullOrTeacherIdIsNot(teacherId);
		m.addAttribute("teacher", teacher);
		m.addAttribute("subjects", subjects);
		m.addAttribute("subjectsNotTaught", subjectsNotTaught);
		return "teacher/addSubject_teacher";
	}

	@GetMapping("addSubject/{teacherId}/{subjectId}")
	public String addSubject(@PathVariable long teacherId, @PathVariable long subjectId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		Subject subject = this.subjectRepository.findOne(subjectId);		
		subject.getTeacher().add(teacher);
		this.subjectRepository.save(subject);
		return "redirect:/teacher/addSubject/{teacherId}";
	}	

	
}

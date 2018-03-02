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

import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.SubjectRepository;

@Controller
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SessionManager sessionManager;

	@GetMapping("/all")
	public String all(Model m) {
		return "subject/all_subjects";
	}

	@GetMapping("/create")
	public String createSubject(Model m) {
		m.addAttribute("subject", new Subject());
		return "subject/new_subject";
	}

	@PostMapping("/create")
	public String createSubjectPost(@Valid @ModelAttribute Subject subject, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "subject/new_subject";
		}
		this.subjectRepository.save(subject);
		return "index";
	}

	@GetMapping("/view/{subjectId}")
	public String viewSubject(Model m, @PathVariable long subjectId) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("subject", subject);
		return "subject/show_subject";
	}

	@GetMapping("/update/{subjectId}")
	public String updateSubject(Model m, @PathVariable long subjectId) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("subject", subject);
		return "subject/edit_subject";
	}

	@PostMapping("/update/{subjectId}")
	public String updateSubjectPost(@Valid @ModelAttribute Subject subject, BindingResult bindingResult,
			@PathVariable long subjectId) {
		if (bindingResult.hasErrors()) {
			return "subject/edit_subject";
		}
		subject.setId(subjectId);
		this.subjectRepository.save(subject);
		return "index";
	}
	
	@GetMapping("/delete/{subjectId}")
	public String deleteSubject(@PathVariable long subjectId, Model m) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("subject", subject);
		return "subject/confirmdelete_subject";
	}

	@PostMapping("/delete/{subjectId}")
	public String deleteSubject(@PathVariable long subjectId) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		if (subject.getSchool() != null || subject.getTeacher().size() > 0 || subject.getMark().size() > 0 || subject.getDivision().size() > 0) {
			return "errors/deleteException";
		}
		this.subjectRepository.delete(subjectId);
		return "redirect:/subject/all";
	}
	
	
	@ModelAttribute("availableSubjects")
	public List<Subject> getSubject() {
		return this.subjectRepository.findAll();
	}

}

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
import pl.schoolmanager.repository.SubjectRepository;

@Controller
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@GetMapping("/all")
	public String all(Model m) {
		return "subject/all_subjects";
	}
	
	
	// CREATE
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

	// READ
	@GetMapping("/view/{subjectId}")
	public String viewSubject(Model m, @PathVariable long subjectId) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("subject", subject);
		return "subject/show_subject";
	}

	// UPDATE
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

	// DELETE
	@GetMapping("/delete/{subjectId}")
	public String deleteSubject(@PathVariable long subjectId) {
		this.subjectRepository.delete(subjectId);
		return "index";
	}
	
	// SHOW ALL
	@ModelAttribute("availableSubjects")
	public List<Subject> getSubject() {
		return this.subjectRepository.findAll();
	}
	
	
	
	
}

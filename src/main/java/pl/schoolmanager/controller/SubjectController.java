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

	// CREATE
	@GetMapping("/create")
	public String createSubject(Model m) {
		m.addAttribute("subject", new Subject());
		return "subject/new_subject"; // view to be developed
	}

	@PostMapping("/create")
	public String createSubjectPost(@Valid @ModelAttribute Subject subject, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "subject/new_subject"; // view to be developed
		}
		this.subjectRepository.save(subject);
		return "index"; // to decide where to return
	}

	// READ
	@GetMapping("/view/{subjectId}")
	public String viewSubject(Model m, @PathVariable long subjectId) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("subject", subject);
		return "subject/show_subject"; // view to be developed
	}

	// UPDATE
	@GetMapping("/update/{subjectId}")
	public String updateSubject(Model m, @PathVariable long subjectId) {
		Subject subject = this.subjectRepository.findOne(subjectId);
		m.addAttribute("subject", subject);
		return "subject/edit_subject"; // view to be developed
	}

	@PostMapping("/update/{subjectId}")
	public String updateSubjectPost(@Valid @ModelAttribute Subject subject, BindingResult bindingResult,
			@PathVariable long subjectId) {
		if (bindingResult.hasErrors()) {
			return "subject/edit_subject"; // view to be developed
		}
		subject.setId(subjectId);
		this.subjectRepository.save(subject);
		return "index"; // to decide where to return
	}

	// DELETE
	@GetMapping("/delete/{subjectId}")
	public String deleteSubject(@PathVariable long subjectId) {
		this.subjectRepository.delete(subjectId);
		return "index"; // to decide where to return
	}

	// SHOW ALL
	@ModelAttribute("availableSubjects")
	public List<Subject> getSubject() {
		return this.subjectRepository.findAll();
	}
	
	@GetMapping("/all")
	public String all(Model m) {
		return "subject/all_subjects";
	}
}

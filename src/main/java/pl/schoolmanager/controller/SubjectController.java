package pl.schoolmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.SubjectRepository;
import pl.schoolmanager.repository.UserRepository;

@Controller
@RequestMapping("/subject")
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@GetMapping("/all")
	public String all(Model m) {
		return "subject/all_subjects";
	}
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;
	
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
	public String deleteSubject(@PathVariable long subjectId) {
		this.subjectRepository.delete(subjectId);
		return "index";
	}
	
	@ModelAttribute("availableSubjects")
	public List<Subject> getSubject() {
		return this.subjectRepository.findAll();
	}
	
	@ModelAttribute("countAllReceivedMessages")
	public Integer countAllReceivedMessages(Long receiverId) {
		return this.messageRepository.findAllByReceiverId(getLoggedUser().getId()).size();
	}

	@ModelAttribute("countAllSendedMessages")
	public Integer countAllSendedMessages(Long senderId) {
		return this.messageRepository.findAllBySenderId(getLoggedUser().getId()).size();
	}
	
	@ModelAttribute("countAllReceivedUnreadedMessages")
	public Integer countAllReceivedUnreadedMessages(Long receiverId, Integer checked) {
		return this.messageRepository.findAllByReceiverIdAndChecked(getLoggedUser().getId(), 0).size();
	}
	
	private User getLoggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
		return this.userRepository.findOneByUsername(username);
	}

}

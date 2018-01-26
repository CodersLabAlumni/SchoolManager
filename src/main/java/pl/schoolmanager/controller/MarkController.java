package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.Mark;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.repository.MarkRepository;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.StudentRepository;
import pl.schoolmanager.repository.SubjectRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/mark")
public class MarkController {

	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SessionManager sessionManager;
	
	@GetMapping("/create")
	public String createMark(@RequestParam long subject, @RequestParam long student, Model m) {
		Subject thisSubject = this.subjectRepository.findOne(subject);
		Student thisStudent = this.studentRepository.findOne(student);
		Mark mark = new Mark();
		mark.setSubject(thisSubject);
		mark.setStudent(thisStudent);
		m.addAttribute("mark", mark);
		return "mark/new_mark";
	}
	
	@PostMapping("/create")
	public String createMarkPost(@Valid @ModelAttribute Mark mark, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "mark/new_mark";
		}
		this.markRepository.save(mark);
		Long divisionId = mark.getStudent().getDivision().getId();
		Long subjectId = mark.getSubject().getId();
		return "redirect:/division/inside/marks/"+divisionId+"/"+subjectId;
	}
	
	@GetMapping("/view/{markId}")
	public String viewMark(Model m, @PathVariable long markId) {
		Mark mark = this.markRepository.findOne(markId);
		m.addAttribute("mark", mark);
		return "mark/show_mark";
	}
	
	@GetMapping("/update/{markId}")
	public String updateMark(@RequestParam long subject, @RequestParam long student, Model m, @PathVariable long markId) {
		Mark mark = this.markRepository.findOne(markId);
		m.addAttribute("mark", mark);
		return "mark/edit_mark";
	}
	
	@PostMapping("/update/{markId}")
	public String updateMarkPost(@Valid @ModelAttribute Mark mark, BindingResult bindingResult, @PathVariable long markId) {
		if (bindingResult.hasErrors()) {
			return "mark/edit_mark";
		}
		mark.setId(markId);
		
		this.markRepository.save(mark);
		Long divisionId = mark.getStudent().getDivision().getId();
		Long subjectId = mark.getSubject().getId();
		return "redirect:/division/inside/marks/"+divisionId+"/"+subjectId;
	}
	
	@DeleteMapping("/delete/{markId}")
	public String deleteMark(@PathVariable long markId) {
		this.markRepository.delete(markId);
		return "index";
	}
	
	@ModelAttribute("availableMarks")
	public List<Mark> getMarks() {
		return this.markRepository.findAll();
	}
	
	@ModelAttribute("countAllReceivedMessages")
	public Integer countAllReceivedMessages(Long receiverId) {
		return this.messageRepository.findAllByReceiverId(sessionManager.loggedUser().getId()).size();
	}

	@ModelAttribute("countAllSendedMessages")
	public Integer countAllSendedMessages(Long senderId) {
		return this.messageRepository.findAllBySenderId(sessionManager.loggedUser().getId()).size();
	}
	
	@ModelAttribute("countAllReceivedUnreadedMessages")
	public Integer countAllReceivedUnreadedMessages(Long receiverId, Integer checked) {
		return this.messageRepository.findAllByReceiverIdAndChecked(sessionManager.loggedUser().getId(), 0).size();
	}
	
}

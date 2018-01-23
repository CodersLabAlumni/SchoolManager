package pl.schoolmanager.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
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

import pl.schoolmanager.entity.Message;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.UserRepository;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	// CREATE
	@GetMapping("/create")
	public String createMessage(Model m) {
		Message message = new Message();
		message.setCreated(new Date());
		m.addAttribute("message", new Message());
		return "message/new_message";
	}

	@PostMapping("/create")
	@Transactional
	public String createMessagePost(@Valid @ModelAttribute Message message, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "message/new_message";
		}
		User receiver = this.userRepository.findOneByEmail(message.getReceiverEmail());
		if (receiver == null) {
			m.addAttribute("errorMessage", "User with this e-mail doesn't exist in database");
			return "message/new_message";
		}
		if (message.getReceiverEmail().equals(getLoggedUser().getEmail())) {
			m.addAttribute("errorMessage", "You cannot send message to yourself");
			return "message/new_message";
		}
		User sender = this.userRepository.findOne(getLoggedUser().getId());
		message.setSender(sender);
		message.setReceiver(receiver);
		this.messageRepository.save(message);
		return "index";
	}
	
	// INBOX
	@GetMapping("/recived")
	public String recivedMessage(Model m) {
		List <Message> receivedMessages= this.messageRepository.findAllByReceiverId(getLoggedUser().getId());
		m.addAttribute("receivedMessages", receivedMessages);
		return "message/received_message";
	}
	
	// OUTBOX
	@GetMapping("/sended")
	public String sendedMessage(Model m) {
		List <Message> sendedMessages= this.messageRepository.findAllBySenderId(getLoggedUser().getId());
		m.addAttribute("sendedMessages", sendedMessages);
		return "message/sended_message";
	}	

	// MESSAGE DETAILS
//	@GetMapping("/view/{messageId}")
//	public String viewMessage(Model m, @PathVariable long teacherId) {
//		Teacher teacher = this.teacherRepository.findOne(teacherId);
//		m.addAttribute("teacher", teacher);
//		return "teacher/show_teacher";
//	}
	
	// DELETE RECEIVED MESSAGES
	@GetMapping("/delete/received/{id}")
	public String deleteReceivedGet(@PathVariable long id, Model m) {
		List <Message> receivedMessages= this.messageRepository.findAllByReceiverId(getLoggedUser().getId());
		Message message = this.messageRepository.findOne(id);
		m.addAttribute("receivedMessages", receivedMessages);
		m.addAttribute("message", message);
		m.addAttribute("del", id);
		return "message/received_message";
	}
	
	@PostMapping("/delete/received/{id}")
	@Transactional
	public String deleteReceivedPost(@PathVariable long id) {
		Message message = this.messageRepository.findOne(id);
		message.setReceiver(null);
		this.messageRepository.save(message);
		return "redirect:/message/recived";
	}

	// DELETE SENDED MESSAGES
	@GetMapping("/delete/sended/{id}")
	public String deleteSendedGet(@PathVariable long id, Model m) {
		List <Message> sendedMessages= this.messageRepository.findAllBySenderId(getLoggedUser().getId());
		Message message = this.messageRepository.findOne(id);
		m.addAttribute("sendedMessages", sendedMessages);
		m.addAttribute("message", message);
		m.addAttribute("del", id);
		return "message/sended_message";
	}
	
	@PostMapping("/delete/sended/{id}")
	@Transactional
	public String deleteSendedPost(@PathVariable long id) {
		Message message = this.messageRepository.findOne(id);
		message.setSender(null);
		this.messageRepository.save(message);
		return "redirect:/message/sended";
	}	
	
	
	private User getLoggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
		return this.userRepository.findOneByUsername(username);
	}

}

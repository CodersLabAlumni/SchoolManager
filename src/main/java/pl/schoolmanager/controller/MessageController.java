package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.Message;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.service.message.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private MessageService messageService;

	@GetMapping("/create")
	public String createMessage(Model m) {
		m.addAttribute("message", new Message());
		return "message/new_message";
	}

	@PostMapping("/create")
	public String createMessage(@ModelAttribute Message message, Model m) {
		try {
			messageService.save(message);
		} catch (IllegalArgumentException e) {
			m.addAttribute("errorMessage", "You cannot send message to yourself or to user that's not exists");
			return "message/new_message";
		}
		return "redirect:/message/sent";
	}

	@GetMapping("/received")
	public String receivedMessages(Model m) {
		m.addAttribute("receivedMessages", messageService.receivedMessages(sessionManager.loggedUser()));
		return "message/received_message";
	}

	@GetMapping("/sent")
	public String sentMessages(Model m) {
		m.addAttribute("sentMessages", messageService.sentMessages(sessionManager.loggedUser()));
		return "message/sent_message";
	}

	@GetMapping("/view/{messageId}")
	public String viewMessage(Model m, @PathVariable long messageId) {
		m.addAttribute("message", messageService.markAsRead(messageId));
		return "message/show_message";
	}

	@GetMapping("/remove/received/{id}")
	public String removeReceivedMessage(@PathVariable long id, Model m) {
		m.addAttribute("receivedMessages", messageService.receivedMessages(sessionManager.loggedUser()));
		m.addAttribute("message", messageService.message(id));
		m.addAttribute("remove", id);
		return "message/received_message";
	}

	@PostMapping("/remove/received/{id}")
	public String removeReceivedMessage(@PathVariable long id) {
		messageService.removeReceiver(id);
		return "redirect:/message/received";
	}

	@GetMapping("/remove/sent/{id}")
	public String removeSentMessage(@PathVariable long id, Model m) {
		m.addAttribute("sentMessages", messageService.sentMessages(sessionManager.loggedUser()));
		m.addAttribute("message", messageService.message(id));
		m.addAttribute("remove", id);
		return "message/sent_message";
	}

	@PostMapping("/remove/sent/{id}")
	public String removeSentMessage(@PathVariable long id) {
		messageService.removeSender(id);
		return "redirect:/message/sent";
	}

}

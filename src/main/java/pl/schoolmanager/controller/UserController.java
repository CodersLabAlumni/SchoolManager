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

import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	// CREATE
	@GetMapping("/create")
	public String createUser(Model m) {
		m.addAttribute("user", new User());
		return "user/new_user"; // view to be developed
	}

	@PostMapping("/create")
	public String createUserPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "user/new_user"; // view to be developed
		}
		this.userRepo.save(user);
		return "redirect:/user/all";
	}
	// READ
	@GetMapping("/view/{userId}")
	public String viewUser(Model m, @PathVariable long userId) {
		User user = this.userRepo.findOne(userId);
		m.addAttribute("user", user);
		return "user/show_user"; // view to be developed
	}

	// UPDATE
	@GetMapping("/update/{userId}")
	public String updateUserRole(Model m, @PathVariable long userId) {
		User user = this.userRepo.findOne(userId);
		m.addAttribute("user", user);
		return "user/new_user"; // view to be developed
	}

	@PostMapping("/update/{userRoleId}")
	public String updateUserPost(@Valid @ModelAttribute User user, BindingResult bindingResult,
			@PathVariable long userId) {
		if (bindingResult.hasErrors()) {
			return "user/new_user"; // view to be developed
		}
		this.userRepo.save(user) ;
		return "redirect:/user/all"; // to decide where to return
	}

	// DELETE
	@GetMapping("/delete/{userId}")
	public String deleteUser(@PathVariable long userId) {
		this.userRepo.delete(userId);
		return "redirect:/user/all"; // to decide where to return
	}

	// SHOW ALL
	@GetMapping("/all")
	public String allUsers(Model m) {
		return "user/all_users";
	}
	
	//Model Attributes
	@ModelAttribute("availableUsers")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}
	
}

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

import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.StudentRepository;
import pl.schoolmanager.repository.UserRepository;
import pl.schoolmanager.repository.UserRoleRepository;

@Controller
@RequestMapping("/userrole")
public class UserRoleController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserRoleRepository userRoleRepo;

	// CREATE
	@GetMapping("/create")
	public String createUserRole(Model m) {
		m.addAttribute("userRole", new UserRole());
		return "userrole/new_userrole"; // view to be developed
	}

	@PostMapping("/create")
	public String createUserRolePost(@Valid @ModelAttribute UserRole userRole, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "userrole/new_userrole"; // view to be developed
		}
		this.userRoleRepo.save(userRole);
		return "redirect:/userrole/all"; // to decide where to return
	}
	// READ
	@GetMapping("/view/{userId}")
	public String viewStudent(Model m, @PathVariable long userId) {
		User user = this.userRepo.findOne(userId);
		m.addAttribute("user", user);
		return "userrole/show_user"; // view to be developed
	}

	// UPDATE
	@GetMapping("/update/{userRoleId}")
	public String updateUserRole(Model m, @PathVariable long userRoleId) {
		UserRole userRole = this.userRoleRepo.findOne(userRoleId);
		m.addAttribute("userRole", userRole);
		return "userrole/new_userrole"; // view to be developed
	}

	@PostMapping("/update/{userRoleId}")
	public String updateUserRolePost(@Valid @ModelAttribute UserRole userRole, BindingResult bindingResult,
			@PathVariable long userRoleId) {
		if (bindingResult.hasErrors()) {
			return "userrole/new_userrole"; // view to be developed
		}
		this.userRoleRepo.save(userRole) ;
		return "redirect:/userrole/all"; // to decide where to return
	}

	// DELETE
	@GetMapping("/delete/{userRoleId}")
	public String deleteStudent(@PathVariable long userRoleId) {
		this.userRoleRepo.delete(userRoleId);
		return "index"; // to decide where to return
	}

	// SHOW ALL
	@GetMapping("/all")
	public String all(Model m) {
		return "userrole/all_users";
	}
	
	//Model Attributes
	@ModelAttribute("availableUsers")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}
	
}

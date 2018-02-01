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

import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.SchoolRepository;
import pl.schoolmanager.repository.UserRepository;
import pl.schoolmanager.repository.UserRoleRepository;

@Controller
@RequestMapping("/userrole")
public class UserRoleController {

	@Autowired
	private UserRoleRepository userRoleRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SchoolRepository schoolRepo;

	@GetMapping("/create/{userId}")
	public String createUserRole(Model m, @PathVariable long userId) {
		User user = this.userRepo.findOne(userId);
		m.addAttribute("user", user);
		m.addAttribute("userRole", new UserRole());
		return "userrole/new_userrole";
	}

	@PostMapping("/create/{userId}")
	public String createUserRolePost(Model m, @PathVariable long userId, 
									@ModelAttribute UserRole userRole,
									BindingResult bindingResult ) {
		if (bindingResult.hasErrors()) {
			return "userrole/new_userrole";
		}
		User user = userRepo.findOne(userId);
		userRole.setUser(user);
		userRole.setUsername(user.getUsername());
		userRoleRepo.save(userRole);
		return "redirect:/user/all";
	}

	@GetMapping("/view/{userRoleId}")
	public String viewUserRolet(Model m, @PathVariable long userRoleId) {
		UserRole userRole = this.userRoleRepo.findOne(userRoleId);
		m.addAttribute("userRole", userRole);
		return "userrole/show_userrole";
	}

	@GetMapping("/update/{userRoleId}")
	public String updateUserRole(Model m, @PathVariable long userRoleId) {
		UserRole userRole = this.userRoleRepo.findOne(userRoleId);
		m.addAttribute("userRole", userRole);
		return "userrole/new_userrole";
	}

	@PostMapping("/update/{userRoleId}")
	public String updateUserRolePost(@Valid @ModelAttribute UserRole userRole, BindingResult bindingResult,
			@PathVariable long userRoleId) {
		if (bindingResult.hasErrors()) {
			return "userrole/new_userrole";
		}
		this.userRoleRepo.save(userRole);
		return "redirect:/userrole/all";
	}

	@GetMapping("/delete/{userRoleId}")
	public String deleteUserRole(@PathVariable long userRoleId, Model m) {
		UserRole userRoleToDelete = userRoleRepo.findOne(userRoleId);
		User user = userRoleToDelete.getUser();
		m.addAttribute("userRoleToDelete", userRoleToDelete);
		m.addAttribute("user", user);
		return "userrole/confirm_delete";
	}
	
	@PostMapping("/delete/{userRoleId}")
	public String deleteUserRolePost(@PathVariable long userRoleId,Model m) {
		this.userRoleRepo.delete(userRoleId);
		return "redirect:/user/all";
	}

	@GetMapping("/all")
	public String allUserRoles(Model m) {
		return "userrole/all_userroles";
	}

	@ModelAttribute("availableUserRoles")
	public List<UserRole> getUserRoles() {
		return this.userRoleRepo.findAll();
	}

	@ModelAttribute("userRolesForSelect")
	public List<String> userRolesForSelect() {
		List<String> userRolesForSelect = UserRole.getRolesForSelect();
		return userRolesForSelect;
	}
	
	@ModelAttribute("availableSchools")
	public List<School> availableSchools() {
		List<School> availableSchools = this.schoolRepo.findAll();
		return availableSchools;
	}

}

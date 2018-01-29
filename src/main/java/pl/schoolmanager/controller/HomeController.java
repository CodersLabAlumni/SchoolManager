package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.bean.role.Role;
import pl.schoolmanager.bean.role.factory.RoleFactory;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.UserRepository;
import pl.schoolmanager.repository.UserRoleRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller("/")
public class HomeController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserRoleRepository userRoleRepo;

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private RoleFactory roleFactory;

	@GetMapping("/")
	public String home() {
		sessionManager.updateMessageValues();
		return "home/home";
	}

	@GetMapping("login")
	public String loginGet() {
		return "home/login";
	}

	@GetMapping("register")
	public String registerGet(Model m) {
		m.addAttribute("user", new User());
		return "home/register";
	}

	@PostMapping("register")
	@Transactional
	public String registerPost(@Valid @ModelAttribute User user, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "redirect:register";
		}
		if (!user.isPasswordCorrent(user.getConfirmPassword())) {
			m.addAttribute("msg", "Please make sure that both passwords match!");
			return "home/register";
		}
		UserRole userRole = roleFactory.get(Role.USER);
		user.setEnabled(true);
		userRole.setUser(user);
		this.userRepo.save(user);
		this.userRoleRepo.save(userRole);
		return "redirect:/login";
	}

	@GetMapping("403")
	public String accessDenied() {
		return "errors/accessDenied";
	}

}
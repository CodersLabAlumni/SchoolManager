package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.UserRepository;

@Controller("/")
public class HomeController {
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/")
	public String home() {
		return "home/home";
	}

	@GetMapping("login")
	public String loginGet() {
		return "home/login";
	}

	@PostMapping("login")
	public String loginPost() {
		return "redirect:/";
	}

	@GetMapping("register")
	public String registerGet(Model m) {
		m.addAttribute("user", new User());
		return "home/register";
	}

	@PostMapping("register")
	public String registerPost(@ModelAttribute User user, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "redirect:register";
		} else {
			user.setUserRole("ROLE_USER");
			user.setEnabled(true);
			this.userRepo.save(user);
			return "redirect:/login";
		}
	}

	@GetMapping("403")
	public String accessDenied() {
		return "errors/accessDenied";
	}
	
	//test mapping
	@GetMapping("test")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String testAccessDenied() {
		return "You managed to get here";
	}
	
}
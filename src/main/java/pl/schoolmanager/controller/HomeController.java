package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.service.user.UserService;
import pl.schoolmanager.validator.user.NewUsernameValidator;
import pl.schoolmanager.validator.user.UserValidation;

@Controller("/")
public class HomeController {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidation userValidation;

    @GetMapping("/")
    public String home() {
        sessionManager.updateMessageValues();
        return "home/home";
    }
    
    @GetMapping("/welcome")
    public String welcome(Model m) {
    	User user = sessionManager.loggedUser();
    	m.addAttribute("user", user);
    	return "home/welcome";
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
    public String registerPost(@Validated(NewUsernameValidator.class) @ModelAttribute User user, BindingResult bindingResult) {
        if (userValidation.validRegister(user, bindingResult).hasErrors()) {
            return "home/register";
        }
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("403")
    public String accessDenied() {
        return "errors/accessDenied";
    }

}
package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.UserRepository;
import pl.schoolmanager.service.user.UserService;
import pl.schoolmanager.validator.user.EditUsernameValidator;
import pl.schoolmanager.validator.user.NewUsernameValidator;
import pl.schoolmanager.validator.user.UserPasswordValidator;
import pl.schoolmanager.validator.user.UserValidation;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidation userValidation;

    @GetMapping("/create")
    public String createUser(Model m) {
        m.addAttribute("user", new User());
        return "home/register";
    }

    @PostMapping("/create")
    public String createUser(@Validated(NewUsernameValidator.class) User user, BindingResult bindingResult) {
        if (userValidation.validRegister(user, bindingResult).hasErrors()) {
            return "home/register";
        }
        userService.register(user);
        return "redirect:/user/all";
    }

    @GetMapping("/view/{userId}")
    public String viewUser(Model m, @PathVariable long userId) {
        m.addAttribute("user", userService.user(userId));
        return "user/show_user";
    }

    @GetMapping("/update/{userId}")
    public String updateUser(Model m, @PathVariable long userId) {
        m.addAttribute("user", userService.user(userId));
        return "user/edit_user";
    }

    @PostMapping("/update/{userId}")
    public String updateUser(@Validated(EditUsernameValidator.class) @ModelAttribute User user, BindingResult bindingResult) {
        if (userValidation.validEdit(user, bindingResult).hasErrors()) {
            return "user/edit_user";
        }
        userService.update(user);
        return "redirect:/user/all";
    }

    @GetMapping("/changepassword/{userId}")
    public String changePassword(@PathVariable long userId, Model m) {
        m.addAttribute("user", userService.user(userId));
        return "user/change_pass";
    }

    @PostMapping("/changepassword/{userId}")
    public String changePassword(@Validated(UserPasswordValidator.class) User user, BindingResult bindingResult, @PathVariable long userId) {
        if (userValidation.validPassword(user, bindingResult).hasErrors()) {
            return "user/change_pass";
        }
        userService.changePassword(userId, user.getPassword());
        return "redirect:/user/all";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable long userId, Model m) {
        m.addAttribute("user", userService.user(userId));
        return "user/confirm_delete";
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable long userId) {
        userService.delete(userId);
        return "redirect:/user/all";
    }

    @GetMapping("/all")
    public String allUsers(Model m) {
        m.addAttribute("availableUsers", userRepo.findAll());
        return "user/all_users";
    }

}

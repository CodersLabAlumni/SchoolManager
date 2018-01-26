package pl.schoolmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
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

import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.SchoolAdmin;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.SchoolAdminRepository;
import pl.schoolmanager.repository.SchoolRepository;
import pl.schoolmanager.repository.SubjectRepository;
import pl.schoolmanager.repository.TeacherRepository;
import pl.schoolmanager.repository.UserRepository;

@Controller
@RequestMapping("/schoolAdmin")
public class SchoolAdminController {

	@Autowired
	private SchoolAdminRepository schoolAdminRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SchoolRepository schoolRepo;

	@Autowired
	private MessageRepository messageRepository;




	// Make new schoolAdmin automatically creating new user role
	@GetMapping("/userNewSchoolAdmin")
	public String newSchoolAdminFromUser(Model m) {
		m.addAttribute("schoolAdmin", new SchoolAdmin());
		HttpSession s = SessionManager.session();
		s.setAttribute("thisSchoolAdmin", null);
		s.setAttribute("thisTeacher", null);
		s.setAttribute("thisStudent", null);
		return "school_admin/user_new_school_admin";
	}

	@PostMapping("/userNewSchoolAdmin")
	public String newTeacherFromUserPost(@Valid @ModelAttribute SchoolAdmin schoolAdmin, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "school_admin/user_new_school_admin";
		}
		User user = getLoggedUser();
		UserRole userRole = new UserRole();
		userRole.setUsername(user.getUsername());
		userRole.setUserRole("ROLE_SCHOOLADMIN");
		userRole.setSchool(schoolAdmin.getSchool());
		userRole.setUser(user);
		schoolAdmin.setUserRole(userRole);
		this.schoolAdminRepository.save(schoolAdmin);
		HttpSession s = SessionManager.session();
		s.setAttribute("thisSchoolAdmin", schoolAdmin);
		s.setAttribute("thisSchool", schoolAdmin.getSchool());
		return "redirect:/schoolAdminView/";
	}
	
	// Managing existing teacher role
	@GetMapping("/userSchoolAdmin")
	public String TeacherFromUser(Model m) {
		m.addAttribute("schoolAdmin", new SchoolAdmin());
		HttpSession s = SessionManager.session();
		s.setAttribute("thisSchoolAdmin", null);
		s.setAttribute("thisTeacher", null);
		s.setAttribute("thisStudent", null);
		return "school_admin/user_school_admin";
	}

	@PostMapping("/userSchoolAdmin")
	public String TeacherFromUserPost(@Valid @ModelAttribute SchoolAdmin schoolAdmin, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "school_admin/user_school_admin";
		}
		User user = getLoggedUser();
		SchoolAdmin thisSchoolAdmin = null;
		UserRole thisUserRole = null;
		List<UserRole> userRoles = user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if (userRole.getUserRole().equals("ROLE_SCHOOLADMIN") && userRole.getSchool().getName().equals(schoolAdmin.getSchool().getName())) {
				thisUserRole = userRole;
			}
		}
		List<SchoolAdmin> schoolAdmins = this.schoolAdminRepository.findAll();
		for (SchoolAdmin s : schoolAdmins) {
			if (s.getUserRole().getId() == thisUserRole.getId()) {
				thisSchoolAdmin = s;
			}
		}
		HttpSession s = SessionManager.session();
		s.setAttribute("thisSchoolAdmin", thisSchoolAdmin);
		s.setAttribute("thisSchool", thisSchoolAdmin.getSchool());
		return "redirect:/schoolAdminView/";
	}

	// Additional methods
	private User getLoggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
		return this.userRepo.findOneByUsername(username);
	}

	@ModelAttribute("availableSchools")
	public List<School> availableSchools() {
		List<School> availableSchools = this.schoolRepo.findAll();
		return availableSchools;
	}
	// MESSAGES INFO
	@ModelAttribute("countAllReceivedMessages")
	public Integer countAllReceivedMessages(Long receiverId) {
		return this.messageRepository.findAllByReceiverId(getLoggedUser().getId()).size();
	}

	@ModelAttribute("countAllSendedMessages")
	public Integer countAllSendedMessages(Long senderId) {
		return this.messageRepository.findAllBySenderId(getLoggedUser().getId()).size();
	}
	
	@ModelAttribute("countAllReceivedUnreadedMessages")
	public Integer countAllReceivedUnreadedMessages(Long receiverId, Integer checked) {
		return this.messageRepository.findAllByReceiverIdAndChecked(getLoggedUser().getId(), 0).size();
	}
	

	@ModelAttribute("userSchools")
	public List<School> userSchools() {
		User user = getLoggedUser();
		List<School> schools = new ArrayList<>();
		List<UserRole> roles = user.getUserRoles();
		for (UserRole userRole : roles) {
			if (userRole.getUserRole().equals("ROLE_SCHOOLADMIN")) {
				schools.add(userRole.getSchool());
			}
		}
		return schools;
	}

}

package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.SchoolRepository;
import pl.schoolmanager.repository.StudentRepository;
import pl.schoolmanager.repository.UserRoleRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SchoolRepository schoolRepo;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SessionManager sessionManager;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	public String createStudent(Model m) {
		m.addAttribute("student", new Student());
		return "student/new_student";
	}

	@PostMapping("/create")
	public String createStudentPost(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "student/new_student";
		}
		this.studentRepository.save(student);
		return "index";
	}

	// Make new student automatically creating new user role
	@GetMapping("/userNewStudent")
	public String newStudentFromUser(Model m) {
		m.addAttribute("student", new Student());
		HttpSession s = SessionManager.session();
		s.setAttribute("thisSchoolAdmin", null);
		s.setAttribute("thisTeacher", null);
		s.setAttribute("thisStudent", null);
		return "student/user_new_student";
	}

	@PostMapping("/userNewStudent")
	public String newStudentFromUserPost(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "student/user_new_student";
		}
		User user = sessionManager.loggedUser();
		if ((this.userRoleRepository.findOneByUserRoleAndUserIdAndSchoolId("ROLE_STUDENT", user.getId(), student.getSchool().getId()))!=null) {
			m.addAttribute("messageToView", "Your profile at this school was earlier created, please wait for activation");
			return "info/infoPage";
			}
		UserRole userRole = new UserRole();
		userRole.setUsername(user.getUsername());
		userRole.setUserRole("ROLE_STUDENT");
		userRole.setSchool(student.getSchool());
		userRole.setUser(user);
		userRole.setEnabled(false);
		student.setUserRole(userRole);
		this.studentRepository.save(student);
		HttpSession s = SessionManager.session();
		s.setAttribute("thisStudent", student);
		s.setAttribute("thisSchool", student.getSchool());
		return "user/user_activation_info";
	}

	// Managing exisitng student role
	@GetMapping("/userStudent")
	public String StudentFromUser(Model m) {
		m.addAttribute("student", new Student());
		HttpSession s = SessionManager.session();
		s.setAttribute("thisSchoolAdmin", null);
		s.setAttribute("thisTeacher", null);
		s.setAttribute("thisStudent", null);
		return "student/user_student";
	}

	@PostMapping("/userStudent")
	public String StudentFromUserPost(@Valid @ModelAttribute Student student, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "student/user_student";
		}
		User user = sessionManager.loggedUser();
		Student thisStudent = null;
		UserRole thisUserRole = null;
		List<UserRole> userRoles = user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if (userRole.getUserRole().equals("ROLE_STUDENT")
					&& userRole.getSchool().getName().equals(student.getSchool().getName())) {
				thisUserRole = userRole;
			}
		}
		List<Student> students = this.studentRepository.findAll();
		for (Student s : students) {
			if (s.getUserRole().getId() == thisUserRole.getId()) {
				thisStudent = s;
			}
		}
		HttpSession s = SessionManager.session();
		s.setAttribute("thisStudent", thisStudent);
		s.setAttribute("thisSchool", student.getSchool());
		return "redirect:/studentView/";
	}

	@GetMapping("/view/{studentId}")
	public String viewStudent(Model m, @PathVariable long studentId) {
		Student student = this.studentRepository.findOne(studentId);
		m.addAttribute("student", student);
		return "student/show_student";
	}

	@GetMapping("/update/{studentId}")
	public String updateStudent(Model m, @PathVariable long studentId) {
		Student student = this.studentRepository.findOne(studentId);
		m.addAttribute("student", student);
		return "student/edit_student";
	}

	@PostMapping("/update/{studentId}")
	public String updateStudentPost(@Valid @ModelAttribute Student student, BindingResult bindingResult,
			@PathVariable long studentId) {
		if (bindingResult.hasErrors()) {
			return "student/edit_student";
		}
		student.setId(studentId);
		this.studentRepository.save(student);
		return "index";
	}

	@GetMapping("/delete/{studentId}")
	public String deleteStudent(@PathVariable long studentId, Model m) {
		Student student = this.studentRepository.findOne(studentId);
		m.addAttribute("student", student);
		return "student/confirmdelete_student";
	}

	@PostMapping("/delete/{studentId}")
	public String deleteStudent(@PathVariable long studentId) {
		Student student = this.studentRepository.findOne(studentId);
		if (student.getSchool() != null || student.getMark() != null || student.getDivision() != null) {
			return "errors/deleteException";
		}
		this.studentRepository.delete(studentId);
		return "redirect:/student/all";
	}

	@ModelAttribute("availableStudents")
	public List<Student> getStudents() {
		return this.studentRepository.findAll();
	}

	// SHOW ALL FROM SCHOOL
	@ModelAttribute("schoolStudents")
	public List<Student> getSchoolStudents() {
		HttpSession s = SessionManager.session();
		School school = (School) s.getAttribute("thisSchool");
		return this.studentRepository.findAllBySchool(school);
	}

	@GetMapping("/all")
	public String all(Model m) {
		return "student/all_students";
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

	// Consider deleting this code
	// @ModelAttribute("userSchools")
	// public List<School> userSchools() {
	// User user = sessionManager.loggedUser();
	// List<School> schools = new ArrayList<>();
	// List<UserRole> roles = user.getUserRoles();
	// for (UserRole userRole : roles) {
	// if (userRole.getUserRole().equals("ROLE_STUDENT")) {
	// schools.add(userRole.getSchool());
	// }
	// }
	// return schools;
	// }

	@ModelAttribute("userEnabledSchools")
	public List<School> userEnabledSchools() {
		User user = sessionManager.loggedUser();
		List<School> schools = new ArrayList<>();
		List<UserRole> roles = user.getUserRoles();
		for (UserRole userRole : roles) {
			if (userRole.getUserRole().equals("ROLE_STUDENT") && userRole.isEnabled() == true) {
				schools.add(userRole.getSchool());
			}
		}
		return schools;
	}

}

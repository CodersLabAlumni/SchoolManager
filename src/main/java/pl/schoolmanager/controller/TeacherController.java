package pl.schoolmanager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.bean.role.Role;
import pl.schoolmanager.entity.*;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.SchoolRepository;
import pl.schoolmanager.repository.SubjectRepository;
import pl.schoolmanager.repository.TeacherRepository;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private SchoolRepository schoolRepo;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private SessionManager sessionManager;

	@GetMapping("/all")
	public String all(Model m) {
		return "teacher/all_teachers";
	}

	@GetMapping("/create")
	public String createTeacher(Model m) {
		m.addAttribute("teacher", new Teacher());
		return "teacher/new_teacher";
	}

	@PostMapping("/create")
	public String createTeacherPost(@Valid @ModelAttribute Teacher teacher, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "teacher/new_teacher";
		}
		this.teacherRepository.save(teacher);
		return "index";
	}

	// Make new teacher automatically creating new user role
	@GetMapping("/userNewTeacher")
	public String newTeacherFromUser(Model m) {
		HttpSession s = SessionManager.session();
		s.setAttribute("thisSchoolAdmin", null);
		s.setAttribute("thisTeacher", null);
		s.setAttribute("thisStudent", null);
		m.addAttribute("teacher", new Teacher());
		return "teacher/user_new_teacher";
	}

	@PostMapping("/userNewTeacher")
	public String newTeacherFromUserPost(@Valid @ModelAttribute Teacher teacher, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "teacher/user_teacher";
		}
		User user = sessionManager.loggedUser();
		UserRole userRole = new UserRole();
		userRole.setUsername(user.getUsername());
		userRole.setUserRole("ROLE_TEACHER");
		userRole.setSchool(teacher.getSchool());
		userRole.setUser(user);
		userRole.setEnabled(false);
		teacher.setUserRole(userRole);
		this.teacherRepository.save(teacher);
		HttpSession s = SessionManager.session();
		s.setAttribute("thisTeacher", teacher);
		s.setAttribute("thisSchool", teacher.getSchool());
		return "user/user_enabling";
	}

	// Managing existing teacher role
	@GetMapping("/userTeacher")
	public String TeacherFromUser(Model m) {
		m.addAttribute("teacher", new Teacher());
		HttpSession s = SessionManager.session();
		s.setAttribute("thisSchoolAdmin", null);
		s.setAttribute("thisTeacher", null);
		s.setAttribute("thisStudent", null);
		return "teacher/user_teacher";
	}

	@PostMapping("/userTeacher")
	public String TeacherFromUserPost(@Valid @ModelAttribute Teacher teacher, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "teacher/user_teacher";
		}
		User user = sessionManager.loggedUser();
		Teacher thisTeacher = null;
		UserRole thisUserRole = null;
		List<UserRole> userRoles = user.getUserRoles();
		for (UserRole userRole : userRoles) {
			if (userRole.getUserRole().equals("ROLE_TEACHER")
					&& userRole.getSchool().getName().equals(teacher.getSchool().getName())) {
				thisUserRole = userRole;
			}
		}
		List<Teacher> teachers = this.teacherRepository.findAll();
		for (Teacher t : teachers) {
			if (t.getUserRole().getId() == thisUserRole.getId()) {
				thisTeacher = t;
			}
		}
		HttpSession s = SessionManager.session();
		s.setAttribute("thisTeacher", thisTeacher);
		s.setAttribute("thisSchool", teacher.getSchool());
		return "redirect:/teacherView/";
	}

	@GetMapping("/view/{teacherId}")
	public String viewTeacher(Model m, @PathVariable long teacherId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		m.addAttribute("teacher", teacher);
		return "teacher/show_teacher";
	}

	@GetMapping("/update/{teacherId}")
	public String updateTeacher(Model m, @PathVariable long teacherId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		m.addAttribute("teacher", teacher);
		return "teacher/edit_teacher";
	}

	@PostMapping("/update/{teacherId}")
	public String updateTeacherPost(@Valid @ModelAttribute Teacher teacher, BindingResult bindingResult,
			@PathVariable long teacherId) {
		if (bindingResult.hasErrors()) {
			return "teacher/edit_teacher";
		}
		teacher.setId(teacherId);
		this.teacherRepository.save(teacher);
		return "index";
	}

	@GetMapping("/delete/{teacherId}")
	public String deleteTeacher(@PathVariable long teacherId, Model m) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		m.addAttribute("teacher", teacher);
		return "teacher/confirmdelete_teacher";
	}

	@PostMapping("/delete/{teacherId}")
	public String deleteSchool(@PathVariable long teacherId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		if (teacher.getSubject() != null || teacher.getSchool() != null) {
			return "errors/deleteException";
		}
		this.teacherRepository.delete(teacherId);
		return "redirect:/teacher/all";
	}

	@ModelAttribute("availableTeachers")
	public List<Teacher> getTeachers() {
		return this.teacherRepository.findAll();
	}

	// SHOW ALL FROM SCHOOL
	@ModelAttribute("schoolTeachers")
	public List<Teacher> getSchoolTeachers() {
		HttpSession s = SessionManager.session();
		School school = (School) s.getAttribute("thisSchool");
		return this.teacherRepository.findAllBySchool(school);
	}

	@GetMapping("/addSubject/{teacherId}")
	public String addSubject(Model m, @PathVariable long teacherId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		List<Subject> subjects = this.subjectRepository.findAllByTeacherId(teacherId);
		List<Subject> subjectsNotTaught = this.subjectRepository.findAllByTeacherIdIsNullOrTeacherIdIsNot(teacherId);
		m.addAttribute("teacher", teacher);
		m.addAttribute("subjects", subjects);
		m.addAttribute("subjectsNotTaught", subjectsNotTaught);
		return "teacher/addSubject_teacher";
	}

	@GetMapping("addSubject/{teacherId}/{subjectId}")
	public String addSubject(@PathVariable long teacherId, @PathVariable long subjectId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		Subject subject = this.subjectRepository.findOne(subjectId);
		subject.getTeacher().add(teacher);
		this.subjectRepository.save(subject);
		return "redirect:/teacher/addSubject/{teacherId}";
	}

	@GetMapping("removeSubject/{teacherId}/{subjectId}")
	public String removeSubject(@PathVariable long teacherId, @PathVariable long subjectId) {
		Teacher teacher = this.teacherRepository.findOne(teacherId);
		Subject subject = this.subjectRepository.findOne(subjectId);
		ListIterator<Teacher> teach = subject.getTeacher().listIterator();
		while (teach.hasNext()) {
			if (teach.next().getId() == teacher.getId()) {
				teach.remove();
			}
		}
		this.subjectRepository.save(subject);
		return "redirect:/teacher/addSubject/{teacherId}";
	}

	@ModelAttribute("availableSchools")
	public List<School> availableSchools() {
		List<School> availableSchools = this.schoolRepo.findAll();
		return availableSchools;
	}

	@ModelAttribute("userSchools")
	public List<School> userSchools() {
		User user = sessionManager.loggedUser();
		List<School> schools = new ArrayList<>();
		List<UserRole> roles = user.getUserRoles();
		for (UserRole userRole : roles) {
			if (Role.TEACHER.isEqualTo(userRole.getUserRole())) {
				schools.add(userRole.getSchool());
			}
		}
		return schools;
	}

	@ModelAttribute("userEnabledSchools")
	public List<School> userEnabledSchools() {
		User user = sessionManager.loggedUser();
		List<School> schools = new ArrayList<>();
		List<UserRole> roles = user.getUserRoles();
		for (UserRole userRole : roles) {
			if (userRole.getUserRole().equals("ROLE_TEACHER") && userRole.isEnabled() == true) {
				schools.add(userRole.getSchool());
			}
		}
		return schools;
	}
	
}

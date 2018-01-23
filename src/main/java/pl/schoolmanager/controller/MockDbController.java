package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.SchoolAdmin;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.SchoolAdminRepository;
import pl.schoolmanager.repository.SchoolRepository;
import pl.schoolmanager.repository.StudentRepository;
import pl.schoolmanager.repository.TeacherRepository;
import pl.schoolmanager.repository.UserRepository;
import pl.schoolmanager.repository.UserRoleRepository;

@Controller
@RequestMapping("/mockdb")
public class MockDbController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserRoleRepository userRoleRepo;
	@Autowired
	private SchoolRepository schoolRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private TeacherRepository teacherRepo;
	@Autowired
	private SchoolAdminRepository schoolAdminRepo;

	@GetMapping("/mockdb")
	public String mockUserAndUserRoles() {
		School school1 = new School("I Liceum", "Secondary");
		School school2 = new School("Lincoln High School", "Secondary");
		School school3 = new School("Best Language School", "Private");
		School school4 = new School("Kennedy Primary School", "Primary");
		School school5 = new School("Jagiellonian University", "University");
		this.schoolRepo.save(school1);
		this.schoolRepo.save(school2);
		this.schoolRepo.save(school3);
		this.schoolRepo.save(school4);
		this.schoolRepo.save(school5);

		User admin111 = new User("admin111", "admin111", "admin111@wp.pl", "Adam", "Adminowicz",true);
		User user111 = new User("user111", "user111", "user111@wp.pl", "Jan", "Niezbedny",true);
		User user112 = new User("user112", "user112", "user112@wp.pl", "Napoleon", "Bonaparte",true);
		User user113 = new User("user113", "user113", "user113@wp.pl", "Adas", "Miauczynski",true);
		User user114 = new User("user114", "user114", "user114@wp.pl", "Jessica", "Johnson",true);
		User user115 = new User("user115", "user115", "user115@wp.pl", "Lara", "Palmer",true);
		User user116 = new User("user116", "user116", "user116@wp.pl", "Vincent", "Van Gogh",true);
		User user117 = new User("user117", "user117", "user117@wp.pl", "Mona", "Lisa",true);
		User user118 = new User("user118", "user118", "user118@wp.pl", "Julius", "Caesar",true);
		User user119 = new User("user119", "user119", "user119@wp.pl", "Phillip", "Morris",true);

		this.userRepo.save(admin111);
		this.userRepo.save(user111);
		this.userRepo.save(user112);
		this.userRepo.save(user113);
		this.userRepo.save(user114);
		this.userRepo.save(user115);
		this.userRepo.save(user116);
		this.userRepo.save(user117);
		this.userRepo.save(user118);
		this.userRepo.save(user119);

		//admin of the whole webapp
		UserRole adminRole1 = new UserRole("admin111", "ROLE_ADMIN", admin111, null);
		this.userRoleRepo.save(adminRole1);
		//teacher in two schools
		UserRole userRole1 = new UserRole("user111", "ROLE_TEACHER", user111, school5);
		this.teacherRepo.save(new Teacher(school5, userRole1));
		UserRole userRole2 = new UserRole("user111", "ROLE_TEACHER", user111, school2);
		this.teacherRepo.save(new Teacher(school2, userRole2));
		//student in two schools
		UserRole userRole3 = new UserRole("user112", "ROLE_STUDENT", user112, school5);
		this.studentRepo.save(new Student(school5, userRole3));
		UserRole userRole4 = new UserRole("user112", "ROLE_STUDENT", user112, school3);
		this.studentRepo.save(new Student(school3, userRole4));
		//owner of one school, teacher in two schools and student in one of them
		UserRole userRole5 = new UserRole("user113", "ROLE_SCHOOLADMIN", user113, school3);
		this.schoolAdminRepo.save(new SchoolAdmin(school3, userRole5));
		UserRole userRole6 = new UserRole("user113", "ROLE_TEACHER", user113, school2);
		this.teacherRepo.save(new Teacher(school2, userRole6));
		UserRole userRole7 = new UserRole("user113", "ROLE_TEACHER", user113, school5);
		this.teacherRepo.save(new Teacher(school5, userRole7));
		UserRole userRole8 = new UserRole("user113", "ROLE_STUDENT", user113, school5);
		this.studentRepo.save(new Student(school5, userRole8));
		//students in one school
		UserRole userRole9 = new UserRole("user114", "ROLE_STUDENT", user114, school5);
		this.studentRepo.save(new Student(school5, userRole9));
		UserRole userRole10 = new UserRole("user115", "ROLE_STUDENT", user115, school3);
		this.studentRepo.save(new Student(school3, userRole10));
		UserRole userRole11 = new UserRole("user116", "ROLE_STUDENT", user116, school3);
		this.studentRepo.save(new Student(school3, userRole11));
		UserRole userRole12 = new UserRole("user117", "ROLE_STUDENT", user117, school5);
		this.studentRepo.save(new Student(school5, userRole12));
		UserRole userRole13 = new UserRole("user118", "ROLE_STUDENT", user118, school5);
		this.studentRepo.save(new Student(school5, userRole13));
		//one school admin
		UserRole userRole14 = new UserRole("user119", "ROLE_SCHOOLADMIN", user119, school5);
		this.schoolAdminRepo.save(new SchoolAdmin(school5, userRole14));
		
		return "redirect:/user/all";
	}
}

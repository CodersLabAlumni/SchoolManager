package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.SchoolRepository;
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

	@GetMapping("/user")
	public String mockUserAndUserRoles() {
		User admin1 = new User().setUsername("admin1").setPassword("admin1")
				.setEmail("admin1@wp.pl").setFirstName("Adam").setLastName("Adminowski")
				.setEnabled(true);
		UserRole adminRole1 = new UserRole().setUser(admin1).setUsername(admin1.getUsername())
				.setUserRole("ROLE_ADMIN");
		this.userRepo.save(admin1);
		this.userRoleRepo.save(adminRole1);
		School school1 = new School()
		
		return "redirect:/user/all";
	}
		
}

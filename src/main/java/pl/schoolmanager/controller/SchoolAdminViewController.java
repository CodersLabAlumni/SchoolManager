package pl.schoolmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.SchoolAdmin;
import pl.schoolmanager.repository.SchoolAdminRepository;

@Controller
@RequestMapping("/schoolAdminView")
public class SchoolAdminViewController {
	
	@Autowired
	private SchoolAdminRepository schoolAdminRepo;

	@GetMapping("/access/{schooladminId}")
	public String schoolAdminHome(@PathVariable long schooladminId, Model m) {
		SchoolAdmin schoolAdmin = schoolAdminRepo.findOne(schooladminId);
		m.addAttribute("schoolAdmin", schoolAdmin);
		return "school_admin_view/school";
	}
}
package pl.schoolmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schoolAdminView")
public class SchoolAdminViewController {

	@GetMapping("/")
	public String schoolAdminHome() {
		return "school_admin_view/school";
	}
}

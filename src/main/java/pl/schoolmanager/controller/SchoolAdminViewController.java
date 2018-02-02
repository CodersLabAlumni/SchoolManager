package pl.schoolmanager.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.SchoolAdmin;
import pl.schoolmanager.repository.DivisionRepository;
import pl.schoolmanager.repository.SchoolAdminRepository;

@Controller
@RequestMapping("/schoolAdminView")
@Transactional
public class SchoolAdminViewController {
	
	@Autowired
	private SchoolAdminRepository schoolAdminRepo;
	@Autowired
	private DivisionRepository divisionRepo;

	@GetMapping("/access/{schooladminId}")
	public String schoolAdminHome(@PathVariable long schooladminId, Model m) {
		SchoolAdmin schoolAdmin = schoolAdminRepo.findOne(schooladminId);
		Hibernate.initialize(schoolAdmin.getSchool().getDivision());
		Hibernate.initialize(schoolAdmin.getSchool().getTeacher());
		Hibernate.initialize(schoolAdmin.getSchool().getStudent());
		Hibernate.initialize(schoolAdmin.getSchool().getSubject());
		m.addAttribute("schoolAdmin", schoolAdmin);
		return "school_admin_view/school";
	}
	
	@GetMapping("/{schooladminId}/createDivision")
	public String addDivisionGet(@PathVariable long schooladminId, Model m) {
		m.addAttribute("division", new Division());
		return "school_admin_view/create_division";
	}
	
	@PostMapping("/{schooladminId}/createDivision")
	public String addDivisionPost(@PathVariable long schooladminId, Model m, 
			@Valid @ModelAttribute Division division, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "school_admin_view/create_division";
		}
		School school = this.schoolAdminRepo.findOne(schooladminId).getSchool();
		division.setSchool(school);
		this.divisionRepo.save(division);
		return "redirect:/schoolAdminView/access/{schooladminId}";
	}
	
	@GetMapping("/{schooladminId}/updateDivision/{divisionId}")
	public String updateDivisionGet(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepo.findOne(divisionId);
		m.addAttribute("division", division);
		return "school_admin_view/create_division";
	}
	
	@PostMapping("/{schooladminId}/updateDivision/{divisionId}")
	public String updateDivisionPost(Model m, @PathVariable long schooladminId, @PathVariable long divisionId,
			@ModelAttribute Division division, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "school_admin_view/create_division";
		}
		School school = this.schoolAdminRepo.findOne(schooladminId).getSchool();
		division.setSchool(school);
		this.divisionRepo.save(division);
		return "redirect:/schoolAdminView/access/{schooladminId}";
	}
	
}
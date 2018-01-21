package pl.schoolmanager.controller;

import java.util.List;

import javax.validation.Valid;

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
import pl.schoolmanager.repository.DivisionRepository;
import pl.schoolmanager.repository.SchoolRepository;

@Controller
@RequestMapping("/school")
public class SchoolController {

	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private DivisionRepository divisionRepository;
	
	//CREATE
		@GetMapping("/create")
		public String createSchool(Model m) {
			m.addAttribute("school", new School());
			return "school/new_school";
		}
		
		@PostMapping("/create")
		public String createSchoolPost(@Valid @ModelAttribute School school, BindingResult bindingResult, Model m) {
			if (bindingResult.hasErrors()) {
				return "school/new_school";
			}
			this.schoolRepository.save(school);
			return "index";
		}
		
		//READ
		@GetMapping("/view/{schoolId}")
		public String viewSchool(Model m, @PathVariable long schoolId) {
			School school = this.schoolRepository.findOne(schoolId);
			m.addAttribute("school", school);
			return "school/show_school";
		}
		
		//UPDATE
		@GetMapping("/update/{schoolId}")
		public String updateSchool(Model m, @PathVariable long schoolId) {
			School school = this.schoolRepository.findOne(schoolId);
			m.addAttribute("school", school);
			return "school/edit_school";
		}
		
		@PostMapping("/update/{schoolId}")
		public String updateSchoolPost(@Valid @ModelAttribute School school, BindingResult bindingResult, @PathVariable long schoolId) {
			if (bindingResult.hasErrors()) {
				return "school/edit_school";
			}
			school.setId(schoolId);
			this.schoolRepository.save(school);
			return "index";
		}
		
		//DELETE
		@GetMapping("/delete/{schoolId}")
		public String deleteSchool(@PathVariable long schoolId) {
			this.schoolRepository.delete(schoolId);
			return "index";
		}
		
		//ADD DIVISION TO SCHOOL
		@GetMapping("/addDivision/{schoolId}")
		public String addSubject(Model m, @PathVariable long schoolId) {
			School school = this.schoolRepository.findOne(schoolId);
			List<Division> schoolDivisions = this.divisionRepository.findAllBySchoolId(schoolId);
			List<Division> freeDivisions = this.divisionRepository.findAllBySchoolIdIsNull();
			m.addAttribute("school", school);
			m.addAttribute("schoolDivisions", schoolDivisions);
			m.addAttribute("freeDivisions", freeDivisions);
			return "school/addDivision_school";
		}
		
//		@GetMapping("/addSubject/{schoolId}")
//		
//		@GetMapping("/addStudent/{schoolId}")
//		
//		@GetMapping("/addTeacher/{schoolId}")
		
		//SHOW ALL
		@ModelAttribute("availableSchools")
		public List<School> getSchools() {
			return this.schoolRepository.findAll();
		}
		
		@GetMapping("/all")
		public String all(Model m) {
			return "school/all_schools";
		}
}

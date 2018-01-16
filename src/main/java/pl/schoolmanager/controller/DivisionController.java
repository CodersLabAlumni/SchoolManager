package pl.schoolmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.entity.Division;
import pl.schoolmanager.repository.DivisionRepository;

@Controller
@RequestMapping("/division")
public class DivisionController {

	@Autowired
	private DivisionRepository divisionRepository;

	//CREATE
	@GetMapping("/create")
	public String createDivision(Model m) {
		m.addAttribute("division", new Division());
		return "division/new_division"; //view to be developed
	}
	
	@PostMapping("/create")
	public String createDivisionPost(@Valid @ModelAttribute Division division, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "division/new_division"; //view to be developed
		}
		this.divisionRepository.save(division);
		return "index"; //to decide where to return
	}
	
	//READ
	@GetMapping("/view/{divisionId}")
	public String viewDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		m.addAttribute("division", division);
		return "division/show_division"; //view to be developed
	}
	
	//UPDATE
	@GetMapping("/update/{divisionId}")
	public String updateDivision(Model m, @PathVariable long divisionId) {
		Division division = this.divisionRepository.findOne(divisionId);
		m.addAttribute("division", division);
		return "division/edit_division"; //view to be developed
	}
	
	@PostMapping("/update/{divisionId}")
	public String updateDivisionPost(@Valid @ModelAttribute Division division, BindingResult bindingResult, @PathVariable long divisionId) {
		if (bindingResult.hasErrors()) {
			return "division/edit_division"; //view to be developed
		}
		division.setId(divisionId);
		this.divisionRepository.save(division);
		return "index"; //to decide where to return
	}
	
	//DELETE
	@DeleteMapping("/delete/{divisionId}")
	public String deleteDivision(@PathVariable long divisionId) {
		this.divisionRepository.delete(divisionId);
		return "index"; //to decide where to return
	}
	
	//SHOW ALL
	@ModelAttribute("availableDivisions")
	public List<Division> getDivisions() {
		return this.divisionRepository.findAll();
	}
}

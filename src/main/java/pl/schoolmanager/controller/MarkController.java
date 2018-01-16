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

import pl.schoolmanager.entity.Mark;
import pl.schoolmanager.repository.MarkRepository;

@Controller
@RequestMapping("/mark")
public class MarkController {

	@Autowired
	private MarkRepository markRepository;
	
	//CREATE
	@GetMapping("/create")
	public String createMark(Model m) {
		m.addAttribute("mark", new Mark());
		return "mark/new_mark"; //view to be developed
	}
	
	@PostMapping("/create")
	public String createMarkPost(@Valid @ModelAttribute Mark mark, BindingResult bindingResult, Model m) {
		if (bindingResult.hasErrors()) {
			return "mark/new_mark"; //view to be developed
		}
		this.markRepository.save(mark);
		return "index"; //to decide where to return
	}
	
	//READ
	@GetMapping("/view/{markId}")
	public String viewMark(Model m, @PathVariable long markId) {
		Mark mark = this.markRepository.findOne(markId);
		m.addAttribute("mark", mark);
		return "mark/show_mark"; //view to be developed
	}
	
	//UPDATE
	@GetMapping("/update/{markId}")
	public String updateMark(Model m, @PathVariable long markId) {
		Mark mark = this.markRepository.findOne(markId);
		m.addAttribute("mark", mark);
		return "mark/edit_mark"; //view to be developed
	}
	
	@PostMapping("/update/{markId}")
	public String updateMarkPost(@Valid @ModelAttribute Mark mark, BindingResult bindingResult, @PathVariable long markId) {
		if (bindingResult.hasErrors()) {
			return "mark/edit_mark"; //view to be developed
		}
		mark.setId(markId);
		this.markRepository.save(mark);
		return "index"; //to decide where to return
	}
	
	//DELETE
	@DeleteMapping("/delete/{markId}")
	public String deleteDivision(@PathVariable long markId) {
		this.markRepository.delete(markId);
		return "index"; //to decide where to return
	}
	
	@ModelAttribute("availableMarks")
	public List<Mark> getMarks() {
		return this.markRepository.findAll();
	}
}

package pl.schoolmanager.controller;

import java.time.DayOfWeek;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.schoolmanager.bean.SessionManager;
import pl.schoolmanager.entity.Schedule;
import pl.schoolmanager.entity.Student;
import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.repository.ScheduleRepository;
import pl.schoolmanager.repository.StudentRepository;

@Controller
@RequestMapping("/studentView")
public class StudentViewController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	

	@GetMapping("")
	public String stidentHome(Model m) {
		return "student_view/division_students";
	}


	@GetMapping("/division")
	public String all(Model m) {
		return "student_view/division_students";
	}
	
	@GetMapping("/subjects")
	public String subject(Model m) {
		return "student_view/subjects_student";
	}
	
	@GetMapping("/marks")
	public String marks(Model m) {
		return "student_view/marks_student";
	}
	
	@ModelAttribute("mondaySubjects")
	public Map<Integer, Subject> getMondaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.FRIDAY);
		if (schedule != null) {
			return schedule.getDaySubject();			
		} else {
			return null;
		}
	}

}

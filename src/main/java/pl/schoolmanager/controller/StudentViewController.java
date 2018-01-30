package pl.schoolmanager.controller;

import java.time.DayOfWeek;
import java.util.HashMap;
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
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.MONDAY);
		if (schedule != null) {
			return schedule.getDaySubject();			
		} else {
			return null;
		}
	}
	
	@ModelAttribute("tuesdaySubjects")
	public Map<Integer, Subject> getTuesdaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.TUESDAY);
		if (schedule != null) {
			return schedule.getDaySubject();			
		} else {
			return null;
		}
	}
	
	@ModelAttribute("wednesdaySubjects")
	public Map<Integer, Subject> getWednesdaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.WEDNESDAY);
		if (schedule != null) {
			return schedule.getDaySubject();			
		} else {
			Subject subject1 = new Subject();
			subject1.setName("lalala");
			Map<Integer, Subject> testMap = new HashMap<>();
			testMap.put(1, subject1);
			testMap.put(2, subject1);
			testMap.put(3, subject1);
			testMap.put(4, subject1);
			testMap.put(5, subject1);
			return testMap;
		}
	}
	
	@ModelAttribute("thursdaySubjects")
	public Map<Integer, Subject> getThursdaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.THURSDAY);
		if (schedule != null) {
			return schedule.getDaySubject();			
		} else {
			Subject subject1 = new Subject();
			subject1.setName("lalala");
			Map<Integer, Subject> testMap = new HashMap<>();
			testMap.put(1, subject1);
			testMap.put(2, subject1);
			testMap.put(3, subject1);
			testMap.put(4, subject1);
			testMap.put(5, subject1);
			return testMap;
		}
	}
	
	@ModelAttribute("fridaySubjects")
	public Map<Integer, Subject> getFridaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.FRIDAY);
		if (schedule != null) {
			return schedule.getDaySubject();			
		} else {
			return null;
		}
	}
	
	@ModelAttribute("testSubject")
	public String getTestSubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.FRIDAY);
		if (schedule != null) {
			return schedule.getDaySubject().get(1).getName();			
		} else {
			return "ni ma";
		}
	}

}

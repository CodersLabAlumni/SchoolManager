package pl.schoolmanager.controller;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/access/{studentId}")
	public String access(@PathVariable long studentId, Model m) {
		Student student = this.studentRepository.findOne(studentId);
		m.addAttribute("student", student);
		return "student_view/school";
	}
	
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
	
//	@ModelAttribute("mondaySubjects")
	public List<String> getMondaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.MONDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
//	@ModelAttribute("tuesdaySubjects")
	public List<String> getTuesdaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.TUESDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
//	@ModelAttribute("wednesdaySubjects")
	public List<String> getWednesdaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.WEDNESDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
//	@ModelAttribute("thursdaySubjects")
	public List<String> getThursdaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.THURSDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
//	@ModelAttribute("fridaySubjects")
	public List<String> getFridaySubject() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.FRIDAY);
		if (schedule == null) {
			schedule = new Schedule();			
		}
		List<String> daySubjects = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			Subject currentSubject = schedule.getDaySubject().get(i);
			if (currentSubject != null) {
				daySubjects.add(currentSubject.getName());
			} else {
				daySubjects.add("empty");
			}
		}
		return daySubjects;
	}
	
//	@ModelAttribute("testSubject1")
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
	
//	@ModelAttribute("testSubject2")
	public String getTestSubject2() {
		HttpSession s = SessionManager.session();
		Student thisStudent = (Student) s.getAttribute("thisStudent");
		Schedule schedule = this.scheduleRepository.findOneByDivisionAndDay(thisStudent.getDivision(), DayOfWeek.FRIDAY);
		if (schedule != null) {
			return schedule.getDaySubject().get(2).getName();			
		} else {
			return "ni ma";
		}
	}

}

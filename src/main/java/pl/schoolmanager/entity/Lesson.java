package pl.schoolmanager.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lesson")
public class Lesson {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "division_id")
	private Division division;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	private Date startDate;
	
	private Date stopDate;
	
	private String dayOfWeek;
	
	private Time startHour;

	public Lesson() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Time getStartHour() {
		return startHour;
	}

	public void setStartHour(Time startHour) {
		this.startHour = startHour;
	}
	
}

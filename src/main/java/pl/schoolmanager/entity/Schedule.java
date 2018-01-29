package pl.schoolmanager.entity;

import java.time.DayOfWeek;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private DayOfWeek day;
	
	@ManyToMany
	private Map<Integer, Subject> daySubject;

	public Schedule() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public Map<Integer, Subject> getDaySubject() {
		return daySubject;
	}

	public void setDaySubject(Map<Integer, Subject> daySubject) {
		this.daySubject = daySubject;
	}
	
	
}

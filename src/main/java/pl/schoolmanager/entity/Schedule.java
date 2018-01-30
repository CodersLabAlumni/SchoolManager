package pl.schoolmanager.entity;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private DayOfWeek day;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "division_id")
	private Division division;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private Map<Integer, Subject> daySubject = new HashMap<Integer, Subject>();

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

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}
	
	
}

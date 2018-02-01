package pl.schoolmanager.entity;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalTime start;
	
	@OneToOne
	@MapsId
	private School school;
	
	private Map<Integer, Integer> breaks = new HashMap<>();

	public TimeTable() {
		super();
		this.start = LocalTime.of(7, 30);
		this.breaks.put(1, 5);
		this.breaks.put(2, 10);
		this.breaks.put(3, 5);
		this.breaks.put(4, 10);
		this.breaks.put(5, 5);
		this.breaks.put(6, 15);
		this.breaks.put(7, 5);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public Map<Integer, Integer> getBreaks() {
		return breaks;
	}

	public void setBreaks(Map<Integer, Integer> breaks) {
		this.breaks = breaks;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	
}

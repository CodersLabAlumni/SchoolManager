package pl.schoolmanager.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "school")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String type;
	
	@OneToMany (mappedBy = "school", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Division> division = new ArrayList<>();
	
	@OneToMany (mappedBy = "school", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Subject> subject = new ArrayList<>();
	
	@ManyToMany (mappedBy = "school")
	private Set<Student> student;
	
	@OneToMany (mappedBy = "school")
	private Set<Teacher>teacher;
	
	@OneToOne (mappedBy = "school")
	private TimeTable timeTable;

	public School() {
		super();
	}

	public School(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Division> getDivision() {
		return division;
	}

	public void setDivision(List<Division> division) {
		this.division = division;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public Set<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teacher> teacher) {
		this.teacher = teacher;
	}

	public Set<Student> getStudent() {
		return student;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}
	
	public String getNameForForm() {
		return this.name + " (" +this.type +")";
	}

	public TimeTable getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}
	
}
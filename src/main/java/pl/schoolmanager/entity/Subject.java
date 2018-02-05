package pl.schoolmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;

	@NotBlank
	@NotEmpty
	private String name;

	private String description;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	List<Division> division = new ArrayList<>();

	@OneToMany(mappedBy = "subject", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	List<Mark> mark = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	List<Teacher> teacher = new ArrayList<>();

	public Subject() {
		super();
	}

	public Subject(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public String getFullName() {
		return this.name + " (" + this.description + ")";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Division> getDivision() {
		return division;
	}

	public void setDivision(List<Division> division) {
		this.division = division;
	}

	public List<Mark> getMark() {
		return mark;
	}

	public void setMark(List<Mark> mark) {
		this.mark = mark;
	}

	public List<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", description=" + description + ", school=" + school
				+ ", division=" + division + ", mark=" + mark + ", teacher=" + teacher + "]";
	}

	
	
}
package pl.schoolmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "division")
public class Division {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;

	@NotEmpty
	private String name;

	private String description;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany (mappedBy = "division", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	List <Student> student = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany (mappedBy = "division")
	List <Subject> subject = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany (mappedBy = "division", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	List<Schedule> schedule = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany (mappedBy = "division", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	List<Lesson> lesson = new ArrayList<>();
	
	public Division() {
		super();
	}

	public Division(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public int getNumStudents() {
		return this.student.size();
	}
	
	public int getNumSubjects() {
		return this.subject.size();
	}
	
	public int getNumLessons() {
		return this.lesson.size();
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

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
	public List<Lesson> getLesson() {
		return lesson;
	}

	public void setLesson(List<Lesson> lesson) {
		this.lesson = lesson;
	}

	@Override
	public String toString() {
		return "Division [id=" + id + ", name=" + name + ", description=" + description + ", school=" + school
				+ ", student=" + student + ", subject=" + subject + "]";
	}
	
	

}
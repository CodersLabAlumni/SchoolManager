package pl.schoolmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	private long id;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy = "teacher")
	List<Subject> subject = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	private School school;

	// relation with user role
	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	private UserRole userRole;

	public Teacher() {
		super();
	}

	public Teacher(School school, UserRole userRole) {
		super();
		this.school = school;
		this.userRole = userRole;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
	
}
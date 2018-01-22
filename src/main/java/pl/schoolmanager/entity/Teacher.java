package pl.schoolmanager.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long id;
//	@NotBlank
//	private String password;
//	@NotEmpty
//	@Email
//	@Column(unique = true)
//	private String email;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;

	@ManyToMany(mappedBy = "teacher")
	List<Subject> subject = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<School> school;

	// relation with user role
	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	private UserRole userRole;

	public Teacher() {
		super();
	}

	public Teacher(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id  + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", student=" + "]";
	}

	public Set<School> getSchool() {
		return school;
	}

	public void setSchool(Set<School> school) {
		this.school = school;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
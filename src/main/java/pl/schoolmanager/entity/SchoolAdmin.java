package pl.schoolmanager.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schooladmin")
public class SchoolAdmin {

	@Id
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private School school;

	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	private UserRole userRole;

	public SchoolAdmin() {
		super();
	}
	
	public SchoolAdmin(School school, UserRole userRole) {
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
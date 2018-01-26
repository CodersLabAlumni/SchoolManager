package pl.schoolmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String username;

	@Column(name="user_role")
	private String userRole;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
	private School school;

	public UserRole() {
		super();
	}

	public UserRole(String username, String userRole, User user, School school) {
		super();
		this.username = username;
		this.userRole = userRole;
		this.user = user;
		this.school = school;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public static List<String> getRolesForSelect() {
		List<String> allRoles = new ArrayList<>();
		allRoles.add("ROLE_ADMIN");
		allRoles.add("ROLE_USER");
		allRoles.add("ROLE_SCHOOLADMIN");
		allRoles.add("ROLE_TEACHER");
		allRoles.add("ROLE_STUDENT");
		allRoles.add("ROLE_PARENT");
		return allRoles;
	}

}
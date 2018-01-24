package pl.schoolmanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;

import pl.schoolmanager.validator.EditUsernameValidator;
import pl.schoolmanager.validator.NewUsernameValidator;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull(groups = {NewUsernameValidator.class, EditUsernameValidator.class})
	@NotEmpty(groups = {NewUsernameValidator.class, EditUsernameValidator.class})
	@Size(min=5, max=25, groups = {NewUsernameValidator.class, EditUsernameValidator.class})
	@Column(unique = true)
	private String username;
	@NotNull(groups = NewUsernameValidator.class)
	@NotEmpty(groups = NewUsernameValidator.class)
	@Size(min=5, max=100, groups = NewUsernameValidator.class)
	private String password;
	@Transient
	private String confirmPassword;
	@NotNull(groups = {NewUsernameValidator.class, EditUsernameValidator.class})
	@NotEmpty(groups = {NewUsernameValidator.class, EditUsernameValidator.class})
	@Email(groups = {NewUsernameValidator.class, EditUsernameValidator.class})
	@Column(unique = true)
	private String email;
	
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	
	private boolean enabled;
	@OneToMany(mappedBy="user", cascade = CascadeType.MERGE, fetch=FetchType.EAGER)
	private List<UserRole> userRoles;

	public User() {
		super();
	}

	public User(String username, String password, String email, String firstName, String lastName, boolean enabled) {
		super();
		this.username = username;
		this.setPassword(password);
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", enabled=" + enabled + ", userRoles=" + userRoles + "]";
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public void setPasswordEncrypted(String password) {
		this.password = password;
	}
	
	public boolean isPasswordCorrent(String password) {
		return BCrypt.checkpw(password, this.password);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	
	public String getFullName() {
		return this.firstName + " " +this.lastName;
	}
}
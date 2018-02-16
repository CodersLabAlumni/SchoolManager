package pl.schoolmanager.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.UserRoleRepository;

@Component
public class StudentAccess {
	@Autowired
	UserRoleRepository userRoleRepo;
	
	public boolean checkAccess(Authentication auth, long studentId) {
		String username = auth.getName();
		List<UserRole> userRoles = this.userRoleRepo.findAllByUsernameAndUserRole(username, "ROLE_STUDENT");
		for (UserRole userRole : userRoles) {
			if(userRole.getId()==studentId && userRole.isEnabled()) {
				return true;
			}
		}
		return false;
	}
}
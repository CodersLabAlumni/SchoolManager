package pl.schoolmanager.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.UserRoleRepository;

@Component
public class SchoolAdminAccess {
	@Autowired
	UserRoleRepository userRoleRepo;
	
	public boolean checkAccess(Authentication auth, long schoolAdminId) {
		String username = auth.getName();
		List<UserRole> userRoles = this.userRoleRepo.findAllByUsernameAndUserRole(username, "ROLE_SCHOOLADMIN");
		for (UserRole userRole : userRoles) {
			if(userRole.getId()==schoolAdminId) {
				return true;
			}
		}
		return false;
	}
}
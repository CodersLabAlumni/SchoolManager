package pl.schoolmanager.bean.role.factory;

import org.springframework.stereotype.Component;
import pl.schoolmanager.bean.role.Role;
import pl.schoolmanager.entity.UserRole;

@Component
public class RoleFactoryImpl implements RoleFactory {

    @Override
    public UserRole get(Role role) {
        UserRole userRole = new UserRole();
        userRole.setUserRole(role.value());
        return userRole;
    }

}

package pl.schoolmanager.bean.role.factory;

import pl.schoolmanager.bean.role.Role;
import pl.schoolmanager.entity.UserRole;

public class RoleFactoryImpl implements RoleFactory {

    @Override
    public UserRole get(Role role) {
        UserRole userRole = new UserRole();
        userRole.setUserRole(role.name());
        return userRole;
    }

}

package pl.schoolmanager.bean.role.factory;

import pl.schoolmanager.bean.role.Role;
import pl.schoolmanager.entity.UserRole;

public interface RoleFactory {

    UserRole get(Role role);

}

package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
}

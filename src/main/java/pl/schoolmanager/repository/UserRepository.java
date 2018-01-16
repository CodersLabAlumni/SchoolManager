package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findOneByUsername(String username);
	
}

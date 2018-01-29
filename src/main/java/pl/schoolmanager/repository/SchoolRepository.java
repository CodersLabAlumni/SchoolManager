package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
	
}

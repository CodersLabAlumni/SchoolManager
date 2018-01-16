package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Division;

public interface DivisionRepository extends JpaRepository<Division, Long> {

}

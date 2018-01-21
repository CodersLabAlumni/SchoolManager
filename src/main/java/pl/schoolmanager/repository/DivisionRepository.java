package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Division;

public interface DivisionRepository extends JpaRepository<Division, Long> {

	List<Division> findAllBySchoolIdIsNull();

	List<Division> findAllBySchoolId(long schoolId);

}

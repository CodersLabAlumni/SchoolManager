package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.School;

public interface DivisionRepository extends JpaRepository<Division, Long> {

	List<Division> findAllBySchoolIdIsNull();

	List<Division> findAllBySchoolId(long schoolId);

	List<Division> findAllBySchool(School school);

}

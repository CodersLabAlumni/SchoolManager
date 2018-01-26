package pl.schoolmanager.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {


	List<Student>findAllByDivisionId(long divisionId);
	List<Student>findAllByDivisionIdIsNull();
	
	Set<Student> findAllBySchoolId(long schoolId);
	Set<Student> findAllBySchoolIdIsNullOrSchoolIdIsNot(long schoolId);
	
	List<Student> findAllBySchool(School school);

}

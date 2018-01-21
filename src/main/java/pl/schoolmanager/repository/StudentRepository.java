package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student>findAllByDivisionId(long divisionId);
	List<Student>findAllByDivisionIdIsNull();
	
	List<Student> findAllBySchoolId(long schoolId);
	List<Student> findAllByTeacherIdIsNullOrTeacherIdIsNot(long schoolId);
}

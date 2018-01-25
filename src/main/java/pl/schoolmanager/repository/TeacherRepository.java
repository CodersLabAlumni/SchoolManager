package pl.schoolmanager.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.School;
import pl.schoolmanager.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Set<Teacher> findAllBySchoolId(long schoolId);

	Set<Teacher> findAllBySchoolIdIsNullOrSchoolIdIsNot(long schoolId);

	List<Teacher> findAllBySchool(School school);

}

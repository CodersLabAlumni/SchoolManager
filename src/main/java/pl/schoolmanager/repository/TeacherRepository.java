package pl.schoolmanager.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Set<Teacher> findAllBySchoolId(long schoolId);

    Set<Teacher> findAllBySchoolIdIsNullOrSchoolIdIsNot(long schoolId);

}

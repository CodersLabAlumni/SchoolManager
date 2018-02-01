package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.entity.Teacher;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByDivisionId(long divisionId);

    List<Subject> findAllByDivisionIdIsNullOrDivisionIdIsNot(long divisionId);

    List<Subject> findAllByTeacherId(long teacherId);

    List<Subject> findAllByTeacherIdIsNullOrTeacherIdIsNot(long teacherId);

    List<Subject> findAllBySchoolId(long schoolId);

    List<Subject> findAllBySchoolIdIsNull();

    List<Subject> findAllByTeacher(Teacher teacher);

}

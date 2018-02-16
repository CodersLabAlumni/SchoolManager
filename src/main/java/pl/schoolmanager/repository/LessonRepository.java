package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Lesson;
import pl.schoolmanager.entity.Teacher;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

	List<Lesson> findAllByTeacher(Teacher teacher);

	List<Lesson> findAllByTeacherId(long teacherId);

}

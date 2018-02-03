package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}

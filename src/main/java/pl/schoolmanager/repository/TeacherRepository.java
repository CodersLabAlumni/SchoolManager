package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}

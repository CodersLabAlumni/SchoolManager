package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

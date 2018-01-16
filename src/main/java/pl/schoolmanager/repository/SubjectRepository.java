package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}

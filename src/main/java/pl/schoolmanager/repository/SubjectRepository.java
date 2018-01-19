package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	List<Subject>findAllByDivisionId(long divisionId);
	List<Subject>findAllByDivisionIdIsNullOrDivisionIdIsNot(long divisionId);
	
}

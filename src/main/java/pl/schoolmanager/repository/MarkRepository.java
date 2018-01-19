package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Mark;
import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {
	
	List<Mark> findAllBySubjectId(long subjectId);
	
}

package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Mark;

public interface MarkRepository extends JpaRepository<Mark, Long> {

}

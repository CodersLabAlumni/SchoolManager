package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}

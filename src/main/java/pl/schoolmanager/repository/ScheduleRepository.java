package pl.schoolmanager.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.Schedule;
import pl.schoolmanager.entity.Subject;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Map<Integer, Subject> findOneByDivisionAndDay(Division division, int day);

}

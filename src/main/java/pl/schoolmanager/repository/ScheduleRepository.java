package pl.schoolmanager.repository;

import java.time.DayOfWeek;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.Schedule;
import pl.schoolmanager.entity.Subject;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Schedule findOneByDivisionAndDay(Division division, DayOfWeek day);
}

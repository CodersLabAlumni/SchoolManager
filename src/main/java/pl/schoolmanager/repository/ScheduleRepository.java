package pl.schoolmanager.repository;

import java.time.DayOfWeek;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Division;
import pl.schoolmanager.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Schedule findOneByDivisionAndDay(Division division, DayOfWeek day);

	Schedule findOneByDivision(Division division);

	Schedule findOneByDay(DayOfWeek day);
}

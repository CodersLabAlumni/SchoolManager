package pl.schoolmanager.service.timetable;

import java.util.List;

import pl.schoolmanager.entity.Lesson;

public interface TimetableService {
	public List<String> formatLessonsForSchedule(List<Lesson> lessons);
}
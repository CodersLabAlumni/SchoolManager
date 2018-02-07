package pl.schoolmanager.service.timetable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.schoolmanager.entity.Lesson;

@Component
public class TimetableServiceImpl implements TimetableService {

	@Override
	public List<String> formatLessonsForSchedule(List<Lesson> lessons) {
		List<String> result = new ArrayList<>();
		for (Lesson lesson : lessons) {
			String formattedLesson = "'";
			formattedLesson += lesson.getSubject().getName() +"', '" + lesson.getDayOfWeek()+"', new Date(2018, 2, 5, " +
					getHourStartFormatted(lesson) + "), new Date(2018, 2, 5, " + getHourStopFormatted(lesson) + ")";
			result.add(formattedLesson);
		}
		
		
		return result;
	}

	private String getHourStopFormatted(Lesson lesson) {
		String[] timeArray = lesson.getStartHour().toString().split(":");
		int hourStop = Integer.parseInt(timeArray[0]);
		int minuteStop = Integer.parseInt(timeArray[1]);
		int duration = lesson.getDuration();
		minuteStop = minuteStop+duration;
		if(minuteStop>=60) {
			hourStop += minuteStop/60;
			minuteStop = minuteStop%60;
		}
		return ""+hourStop + ", " + minuteStop;
	}

	private String getHourStartFormatted(Lesson lesson) {
		String[] timeArray = lesson.getStartHour().toString().split(":");
		String hourFormatted = "" + timeArray[0] + ", " + timeArray[1];
		return hourFormatted;
	}
}
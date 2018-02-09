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

	public String getActiveDays(List<Lesson> lessons) {
		String result = "[ ";
		String[] daysInWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		for (String day : daysInWeek) {
			if (dayIsInLessons(day, lessons)) {
				result += "'"+day+"'";
				if(!day.equals("Sun")) {
					result += ", ";
				}
			}
		}
		result += " ]";
		return result;
	}

	private boolean dayIsInLessons(String day, List<Lesson> lessons) {
		for (Lesson lesson : lessons) {
			if(lesson.getDayOfWeek().equals(day)) {
				return true;
			}
		}
		return false;
	}

	public String getActiveHours(List<Lesson> lessons) {
		int lessonMinHour = getLessonsMinHour(lessons);
		int lessonMaxHour = getLessonsMaxHour(lessons);
		return lessonMinHour + ", " + lessonMaxHour;
	}


	private int getLessonsMinHour(List<Lesson> lessons) {
		int result = 23;
		for (Lesson lesson : lessons) {
			int currentStartHour = Integer.parseInt((lesson.getStartHour().toString().split(":"))[0]);
			if(currentStartHour<result) {
				result = currentStartHour;
			}
		}
		if(result>=1) {
			result -= 1;
		} else {
			result = 0;
		}
		return result;
	}
	
	private int getLessonsMaxHour(List<Lesson> lessons) {
		int result = 0;
		for (Lesson lesson : lessons) {
			int currentStartHour = Integer.parseInt((lesson.getStartHour().toString().split(":"))[0]);
			int minutes = Integer.parseInt((lesson.getStartHour().toString().split(":"))[1]) + lesson.getDuration();
			int currentStopHour = currentStartHour + minutes/60 + 1;
			if(currentStopHour>result) {
				result = currentStopHour;
			}
		}
		if(result<=22) {
			result += 1;
		} else {
			result=23;
		}
		return result;
	}
}
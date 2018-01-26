package pl.schoolmanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.schoolmanager.entity.Teacher;
import pl.schoolmanager.repository.TeacherRepository;

public class TeacherConverter implements Converter<String, Teacher> {

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher convert(String source) {
		return teacherRepository.findOne(Long.parseLong(source));
	}
	
	
}

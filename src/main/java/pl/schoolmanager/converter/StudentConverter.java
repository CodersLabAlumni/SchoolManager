package pl.schoolmanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.schoolmanager.entity.Student;
import pl.schoolmanager.repository.StudentRepository;

public class StudentConverter implements Converter<String, Student> {
	
	@Autowired
	private StudentRepository studRepo;
	
	public Student convert(String source) {
		return studRepo.findOne(Long.parseLong(source));
	}
}
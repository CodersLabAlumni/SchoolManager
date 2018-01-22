package pl.schoolmanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.schoolmanager.entity.School;
import pl.schoolmanager.repository.SchoolRepository;

public class SchoolConverter implements Converter<String, School> {
	
	@Autowired
	private SchoolRepository schoolRepo;
	
	public School convert(String source) {
		return schoolRepo.findOne(Long.parseLong(source));
	}
}
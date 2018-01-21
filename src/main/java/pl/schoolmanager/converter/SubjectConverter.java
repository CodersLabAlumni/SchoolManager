package pl.schoolmanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.schoolmanager.entity.Subject;
import pl.schoolmanager.repository.SubjectRepository;

public class SubjectConverter implements Converter<String, Subject> {
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	public Subject convert(String source) {
		return subjectRepo.findOne(Long.parseLong(source));
	}
}
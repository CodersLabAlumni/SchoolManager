package pl.schoolmanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.schoolmanager.entity.Message;
import pl.schoolmanager.entity.School;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.SchoolRepository;

public class MessageConverter implements Converter<String, Message> {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public Message convert(String source) {
		return messageRepository.findOne(Long.parseLong(source));
	}
}
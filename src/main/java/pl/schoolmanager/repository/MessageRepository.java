package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message>findAllByReceiverId(long receiverId);
	List<Message>findAllByReceiverIdAndChecked(long receiverId, int checked);
	List<Message>findAllBySenderId(long senderId);

	
	
}

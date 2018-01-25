package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiverId(Long receiverId);

    List<Message> findAllByReceiverIdAndChecked(Long receiverId, Integer checked);

    List<Message> findAllBySenderId(Long senderId);

}

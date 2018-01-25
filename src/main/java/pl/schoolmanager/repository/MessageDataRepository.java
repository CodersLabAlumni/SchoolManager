package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.schoolmanager.entity.Message;
import pl.schoolmanager.entity.MessageData;

public interface MessageDataRepository extends JpaRepository<MessageData, Long> {

    MessageData findByMessage(Message message);

    MessageData findByMessageId(long id);

}

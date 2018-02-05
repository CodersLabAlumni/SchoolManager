package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.schoolmanager.entity.Message;
import pl.schoolmanager.entity.MessageResponse;

import java.util.List;

public interface MessageResponseRepository extends JpaRepository<MessageResponse, Long> {

    List<MessageResponse> findAllByMessageOrderByCreatedAsc(Message message);

    List<MessageResponse> findAllByMessageIdOrderByCreatedAsc(long id);

    int countAllByMessage(Message message);

    int countAllByMessageId(long id);

}

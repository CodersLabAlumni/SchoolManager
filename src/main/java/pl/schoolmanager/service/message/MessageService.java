package pl.schoolmanager.service.message;

import pl.schoolmanager.entity.Message;
import pl.schoolmanager.entity.User;

import java.util.List;

public interface MessageService {

    Message save(Message message);

    Message removeReceiver(long messageId);

    Message removeSender(long messageId);

    Message markAsRead(long messageId);

    Message message(long id);

    List<Message> receivedMessages(User user);

    List<Message> sentMessages(User user);

}

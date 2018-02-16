package pl.schoolmanager.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.MessageRepository;
import pl.schoolmanager.repository.UserRepository;

import javax.servlet.http.HttpSession;

@Component
public class SessionManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public static HttpSession session() {
        ServletRequestAttributes attr =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    public User loggedUser() {
        return userRepository.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void updateMessageValues() {
        session().setAttribute("receivedMessages", messageRepository.countByReceiver(loggedUser()));
        session().setAttribute("sentMessages", messageRepository.countBySender(loggedUser()));
        session().setAttribute("unreadInbox", messageRepository.countByReceiverAndOpenByReceiver(loggedUser(), false));
        session().setAttribute("unreadOutbox", messageRepository.countBySenderAndOpenBySender(loggedUser(), false));
    }

}
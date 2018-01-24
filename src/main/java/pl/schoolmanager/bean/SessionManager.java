package pl.schoolmanager.bean;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.UserRepository;

@Component
public class SessionManager {

    @Autowired
    private UserRepository userRepository;

	public static HttpSession session() {
		ServletRequestAttributes attr = 
				(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

    public User loggedUser() {
        return userRepository.findOneByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
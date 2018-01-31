package pl.schoolmanager.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.schoolmanager.bean.role.Role;
import pl.schoolmanager.bean.role.factory.RoleFactory;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.entity.UserRole;
import pl.schoolmanager.repository.UserRepository;
import pl.schoolmanager.repository.UserRoleRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleFactory roleFactory;

    @Override
    public User user(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User register(User user) {
        user.hashPassword();
        user.setEnabled(true);
        UserRole userRole = roleFactory.get(Role.USER);
        userRole.setUser(user);
        userRoleRepository.save(userRole);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        userRoleRepository.updateWithUsernameByUserId(user.getId(), user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User changePassword(long userId, String password) {
        User user = userRepository.findOne(userId);
        user.setPassword(password);
        user.hashPassword();
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

}

package pl.schoolmanager.service.user;

import pl.schoolmanager.entity.User;

public interface UserService {

    User user(long id);

    User register(User user);

    User update(User user);

    void delete(long id);

    User changePassword(long userId, String password);

}

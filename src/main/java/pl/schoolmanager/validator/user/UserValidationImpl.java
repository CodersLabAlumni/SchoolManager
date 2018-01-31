package pl.schoolmanager.validator.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import pl.schoolmanager.entity.User;
import pl.schoolmanager.repository.UserRepository;

@Component
public class UserValidationImpl implements UserValidation {

    @Autowired
    private UserRepository userRepository;

    @Override
    public BindingResult validRegister(User user, BindingResult bindingResult) {
        validUsername(user, bindingResult);
        validEmail(user, bindingResult);
        return validPassword(user, bindingResult);
    }

    @Override
    public BindingResult validEdit(User user, BindingResult bindingResult) {
        validUsername(user, bindingResult);
        return validEmail(user, bindingResult);
    }

    @Override
    public BindingResult validPassword(User user, BindingResult bindingResult) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error.password", "Both passwords must match");
        }
        return bindingResult;
    }

    @Override
    public BindingResult validUsername(User user, BindingResult bindingResult) {
        if (isUsernameExists(user.getUsername()) && !isSameUserUsername(user)) {
            bindingResult.rejectValue("username", "error.username", "Username already taken");
        }
        return bindingResult;
    }

    @Override
    public BindingResult validEmail(User user, BindingResult bindingResult) {
        if (isEmailExists(user.getEmail()) && !isSameUserEmail(user)) {
            bindingResult.rejectValue("email", "error.email", "Email already taken");
        }
        return bindingResult;
    }

    private boolean isUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    private boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isSameUserEmail(User user) {
        return user.getId() == userRepository.findOneByEmail(user.getEmail()).getId();
    }

    private boolean isSameUserUsername(User user) {
        return user.getId() == userRepository.findOneByUsername(user.getUsername()).getId();
    }

}

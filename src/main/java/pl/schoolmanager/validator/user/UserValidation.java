package pl.schoolmanager.validator.user;

import org.springframework.validation.BindingResult;
import pl.schoolmanager.entity.User;

public interface UserValidation {

    BindingResult validRegister(User user, BindingResult bindingResult);

    BindingResult validEdit(User user, BindingResult bindingResult);

    BindingResult validPassword(User user, BindingResult bindingResult);

    BindingResult validUsername(User user, BindingResult bindingResult);

    BindingResult validEmail(User user, BindingResult bindingResult);

}

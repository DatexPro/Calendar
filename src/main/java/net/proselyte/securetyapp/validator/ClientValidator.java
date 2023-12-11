package net.proselyte.securetyapp.validator;

import net.proselyte.securetyapp.model.Client;
import net.proselyte.securetyapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {
    @Autowired
    private ClientService clientService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (client.getName().length() < 6 || client.getName().length() > 32) {
            errors.rejectValue("name", "size.userForm.username");
        }

        if (clientService.findByName(client.getName()) != null) {
            errors.rejectValue("name", "duplicate.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (client.getPassword().length() < 6 || client.getPassword().length() > 32) {
            errors.rejectValue("password", "size.userForm.password");
        }
        if (!client.getConfirmPassword().equals(client.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "duplicate.userForm.password");
        }

    }
}

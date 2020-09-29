package my.spring.exhibitions.validator;

import my.spring.exhibitions.annotation.PasswordMatches;
import my.spring.exhibitions.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {


    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext validatorContext) {
        UserDTO userDTO = (UserDTO) object;
        boolean isValid = userDTO.getPassword().equals(userDTO.getMatchingPassword());
        if (!isValid){
            validatorContext.disableDefaultConstraintViolation();
            validatorContext.buildConstraintViolationWithTemplate(validatorContext.getDefaultConstraintMessageTemplate())
                    .addPropertyNode( "password" ).addConstraintViolation();
        }
        return isValid;
    }
}

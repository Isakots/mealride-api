package hu.student.projlab.mealride_api.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<Password, String> {
 
    @Override
    public void initialize(Password password) {
    }
 
    @Override
    public boolean isValid(String contactField,
      ConstraintValidatorContext cxt) {
        // TODO We need a regexp pattern here later..
        return contactField != null && (contactField.length() >= 5);
    }
 
}

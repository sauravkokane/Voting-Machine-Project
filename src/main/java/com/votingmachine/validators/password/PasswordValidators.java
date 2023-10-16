package com.votingmachine.validators.password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidators implements ConstraintValidator<ValidPassword, String> {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=(.*\\d){2,})(?=.*[@#$%^&+=]).{8,}$");

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false; // Null passwords are considered valid
        }

        // Implement your custom password validation logic using PASSWORD_PATTERN
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}

package pl.edu.wszib.expensetracker.validators;

import pl.edu.wszib.expensetracker.exceptions.AuthValidationException;

public class LoginValidator {
    public static void validateLogin(String login) {
        if (login == null || login.length() <= 1) {
            throw new AuthValidationException("Login incorrect");
        }
    }

    public static void validatePass(String pass) {
        if (pass == null || pass.length() <= 1) {
            throw new AuthValidationException("Password incorrect");
        }
    }
}

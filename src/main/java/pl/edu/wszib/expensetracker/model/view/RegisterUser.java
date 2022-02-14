package pl.edu.wszib.expensetracker.model.view;

import lombok.Getter;
import lombok.Setter;
import pl.edu.wszib.expensetracker.model.User;

public class RegisterUser extends User {
    @Getter
    @Setter
    private String passwordConfirmation;
}

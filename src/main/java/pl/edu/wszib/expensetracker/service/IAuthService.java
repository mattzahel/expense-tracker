package pl.edu.wszib.expensetracker.service;

import pl.edu.wszib.expensetracker.model.view.RegisterUser;

public interface IAuthService {
    void login(String login, String password);
    void register(RegisterUser user);
    void logout();
}

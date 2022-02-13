package pl.edu.wszib.expensetracker.service;

public interface IAuthService {
    boolean login(String login, String password);
    void logout();
}

package pl.edu.wszib.expensetracker.database;

import pl.edu.wszib.expensetracker.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    Optional <User> getUserById(int id);
    void addUser(User user);
}

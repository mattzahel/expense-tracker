package pl.edu.wszib.expensetracker.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.expensetracker.database.IUserDAO;
import pl.edu.wszib.expensetracker.exceptions.LoginAlreadyUseException;
import pl.edu.wszib.expensetracker.model.User;
import pl.edu.wszib.expensetracker.model.view.RegisterUser;
import pl.edu.wszib.expensetracker.service.IAuthService;
import pl.edu.wszib.expensetracker.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    @Resource
    SessionObject sessionObject;

    @Autowired
    IUserDAO userDAO;

    public void login(String login, String password) {
        Optional<User> user = this.userDAO.getUserByLogin(login);

        if (user.isEmpty() || !user.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            return;
        }

        sessionObject.setUser(user.get());
    }

    public void logout() {
        sessionObject.setUser(null);
    }

    public void register(RegisterUser registerUser) {
        Optional<User> userBox = this.userDAO.getUserByLogin(registerUser.getLogin());

        if (userBox.isPresent()) {
            throw new LoginAlreadyUseException();
        }

        registerUser.setPassword(DigestUtils.md5Hex(registerUser.getPassword()));

        User user = new User();
        user.setName(registerUser.getName());
        user.setLogin(registerUser.getLogin());
        user.setPassword(registerUser.getPassword());

        this.userDAO.addUser(user);
    }
}

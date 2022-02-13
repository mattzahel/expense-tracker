package pl.edu.wszib.expensetracker.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.expensetracker.database.IUserDAO;
import pl.edu.wszib.expensetracker.model.User;
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

    public boolean login(String login, String password) {
        Optional<User> user = this.userDAO.getUserByLogin(login);

        if (user.isEmpty() || !user.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            return false;
        }

        this.sessionObject.setUser(user.get());
        return true;
    }

    public void logout() {
        this.sessionObject.setUser(null);
    }
}

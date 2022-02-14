package pl.edu.wszib.expensetracker.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.expensetracker.database.IUserDAO;
import pl.edu.wszib.expensetracker.model.User;
import pl.edu.wszib.expensetracker.service.IAuthService;
import pl.edu.wszib.expensetracker.configuration.TestConfiguration;
import pl.edu.wszib.expensetracker.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class AuthServiceTest {

    @Autowired
    IAuthService authService;

    @Resource
    SessionObject sessionObject;

    @Autowired
    IUserDAO userDAO;

    @Test
    public void correctAuthTest() {
        String login = "admin";
        String password = "secret";

        Mockito.when(this.userDAO.getUserByLogin(login)).thenReturn(generateUser(login, password));
        this.authService.login(login, password);

        Assert.assertTrue(this.sessionObject.isLogged());
    }

    public Optional<User> generateUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(DigestUtils.md5Hex(password));
        user.setName("Test Name");

        return Optional.of(user);
    }
}
package pl.edu.wszib.expensetracker.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.expensetracker.model.User;

@Component
@SessionScope
public class SessionObject {
    private User user = null;

    public boolean isLogged() {
        return this.user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

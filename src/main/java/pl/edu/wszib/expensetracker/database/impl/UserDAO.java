package pl.edu.wszib.expensetracker.database.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.expensetracker.database.IUserDAO;
import org.hibernate.query.Query;
import pl.edu.wszib.expensetracker.model.User;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Optional<User> getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(
                "FROM pl.edu.wszib.expensetracker.model.User WHERE login = :login");
        query.setParameter("login", login);

        try {
            User user = query.getSingleResult();

            session.close();
            return Optional.of(user);
        } catch (NoResultException e) {
            session.close();

            return Optional.empty();
        }
    }

    public Optional<User> getUserById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(
                "FROM pl.edu.wszib.expensetracker.model.User WHERE id = :id");
        query.setParameter("id", id);

        try {
            User user = query.getSingleResult();

            session.close();
            return Optional.of(user);
        } catch (NoResultException e) {
            session.close();

            return Optional.empty();
        }
    }
}

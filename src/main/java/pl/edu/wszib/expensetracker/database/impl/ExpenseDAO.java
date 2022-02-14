package pl.edu.wszib.expensetracker.database.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MappingException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.expensetracker.database.IExpenseDAO;
import pl.edu.wszib.expensetracker.database.IUserDAO;
import pl.edu.wszib.expensetracker.model.Expense;
import pl.edu.wszib.expensetracker.model.User;
import pl.edu.wszib.expensetracker.session.SessionObject;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ExpenseDAO implements IExpenseDAO {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    SessionFactory sessionFactory;

    @Resource
    SessionObject sessionObject;

    @Override
    public void create(String title, double value, String category, String date) {
        User currentUser = sessionObject.getUser();

        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
       try {
           Date expenseDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
           Expense expense = new Expense(1, value, title, category, expenseDate, currentUser);

           tx = session.beginTransaction();
           session.save(expense);
           tx.commit();
       } catch (MappingException | ParseException e) {
           if (tx != null) {
               tx.rollback();
           }

           e.printStackTrace();
       } finally {
           session.close();
       }
   }

   @Override
    public List<Expense> getUserExpenses(int userId) {
       Session session = this.sessionFactory.openSession();
       Query<Expense> query = session.createQuery(
               "SELECT sum(value), date FROM pl.edu.wszib.expensetracker.model.Expense WHERE user.id = :userId " +
                       "GROUP BY date ORDER BY date"
       );
       query.setParameter("userId", userId);
       List<Expense> expenses = query.getResultList();

       session.close();

       return expenses;
   }
}
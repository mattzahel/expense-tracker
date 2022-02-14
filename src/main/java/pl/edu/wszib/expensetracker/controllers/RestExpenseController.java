package pl.edu.wszib.expensetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.expensetracker.database.IExpenseDAO;
import pl.edu.wszib.expensetracker.model.Expense;
import pl.edu.wszib.expensetracker.model.User;
import pl.edu.wszib.expensetracker.session.SessionObject;

import java.util.List;

@RestController
public class RestExpenseController {

    @Autowired
    IExpenseDAO expenseDAO;

    @Autowired
    SessionObject sessionObject;

    @GetMapping(value = "expenses")
    public List<Expense> getUserExpenses() {
        User currentUser = sessionObject.getUser();

        return expenseDAO.getUserExpenses(currentUser.getId());
    }
}

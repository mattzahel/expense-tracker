package pl.edu.wszib.expensetracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.expensetracker.database.IExpenseDAO;
import pl.edu.wszib.expensetracker.service.IExpenseService;

@Service
public class ExpenseService implements IExpenseService {
    @Autowired
    IExpenseDAO expenseDAO;

    public void create(String title, double value, String category, String date) {
        this.expenseDAO.create(title, value, category, date);
    }
}

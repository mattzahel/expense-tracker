package pl.edu.wszib.expensetracker.database;

import pl.edu.wszib.expensetracker.model.Expense;

import java.util.List;

public interface IExpenseDAO {
    void create(String title, double value, String category, String date);
    List<Expense> getUserExpenses(int userid);
}

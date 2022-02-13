package pl.edu.wszib.expensetracker.database;

public interface IExpenseDAO {
    void create(String title, double value, String category, String date);
}

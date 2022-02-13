package pl.edu.wszib.expensetracker.service;

public interface IExpenseService {
    void create(String title, double value, String category, String date);
}

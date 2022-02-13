package pl.edu.wszib.expensetracker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double value;
    private String title;
    private String category;
    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date date;

    public Expense() {}

    public Expense(int id, double value, String title, String category, Date date) {
        this.id = id;
        this.value = value;
        this.title = title;
        this.category = category;
        this.date = date;
    }
}

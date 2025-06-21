package Expense_Share_App.Expense.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Split extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="expense_id",nullable = false)
    @JsonBackReference //to avoid infinite recursion
    private Expense expense;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @Column(name="paid" ,nullable = false)
    private Double paid;

    @Column(name="owed",nullable = false)
    private Double owed;

    public Split(Expense expense, User user, Double paid, Double owed) {
        this.expense = expense;
        this.user = user;
        this.paid = paid;
        this.owed = owed;
    }

    public Split() {
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Double getOwed() {
        return owed;
    }

    public void setOwed(Double owed) {
        this.owed = owed;
    }
}

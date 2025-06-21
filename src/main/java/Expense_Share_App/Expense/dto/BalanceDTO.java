package Expense_Share_App.Expense.dto;
import Expense_Share_App.Expense.models.User;
public class BalanceDTO {

    private User from;
    private User to;
    private Double amount;

    public BalanceDTO(User from, User to, Double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public BalanceDTO() {
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

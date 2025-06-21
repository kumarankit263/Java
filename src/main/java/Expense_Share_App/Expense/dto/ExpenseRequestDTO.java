package Expense_Share_App.Expense.dto;

import Expense_Share_App.Expense.models.Expense;

import java.util.Map;

public class ExpenseRequestDTO {

    private Expense expense;
    private Map<Long, Double> paidMap;       // userId -> amount paid
    private Map<Long, Double> percentageMap; // userId -> percentage (only if PERCENTAGE split)

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Map<Long, Double> getPaidMap() {
        return paidMap;
    }

    public void setPaidMap(Map<Long, Double> paidMap) {
        this.paidMap = paidMap;
    }

    public Map<Long, Double> getPercentageMap() {
        return percentageMap;
    }

    public void setPercentageMap(Map<Long, Double> percentageMap) {
        this.percentageMap = percentageMap;
    }


}

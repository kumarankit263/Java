package Expense_Share_App.Expense.controllers;

import Expense_Share_App.Expense.dto.ExpenseRequestDTO;
import Expense_Share_App.Expense.models.Expense;

import Expense_Share_App.Expense.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public Expense addExpense(@RequestBody ExpenseRequestDTO request) {
        return expenseService.addExpenseWithStrategy(
                request.getExpense(),
                request.getPaidMap(),
                request.getPercentageMap()
        );
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getUserExpenses(@PathVariable Long userId) {
        return expenseService.getUserExpenses(userId);
    }
}

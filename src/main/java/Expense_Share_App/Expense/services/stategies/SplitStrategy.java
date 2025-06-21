package Expense_Share_App.Expense.services.stategies;

import Expense_Share_App.Expense.models.Expense;
import Expense_Share_App.Expense.models.Split;
import Expense_Share_App.Expense.models.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    List<Split> calculateSplits(Expense expense, List<User> users, Map<Long, Double> paidMap, Map<Long, Double> percentageMap);
}

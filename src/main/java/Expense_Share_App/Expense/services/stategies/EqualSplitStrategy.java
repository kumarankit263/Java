package Expense_Share_App.Expense.services.stategies;

import Expense_Share_App.Expense.models.Expense;
import Expense_Share_App.Expense.models.Split;
import Expense_Share_App.Expense.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component("EQUAL")
public class EqualSplitStrategy implements SplitStrategy  {

    @Override
    public List<Split> calculateSplits(Expense expense, List<User> users, Map<Long, Double> paidMap, Map<Long, Double> percentageMap) {
        double perUser = expense.getTotalAmount() / users.size();
        return users.stream()
                .map(user -> new Split(expense, user, paidMap.getOrDefault(user.getId(), 0.0), perUser))
                .collect(Collectors.toList());
    }
}

package Expense_Share_App.Expense.services;

import Expense_Share_App.Expense.models.Expense;
import Expense_Share_App.Expense.models.ExpenseGroup;
import Expense_Share_App.Expense.models.Split;
import Expense_Share_App.Expense.models.User;
import Expense_Share_App.Expense.repositories.ExpenseGroupRepository;
import Expense_Share_App.Expense.repositories.ExpenseRepository;
import Expense_Share_App.Expense.repositories.SplitRepository;
import Expense_Share_App.Expense.repositories.UserRepository;
import Expense_Share_App.Expense.services.stategies.SplitStrategy;
import org.springframework.stereotype.Service;
import java.util.Date;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepo;
    private final ExpenseGroupRepository groupRepo;
    private final SplitRepository splitRepo;
    private final UserRepository userRepo;
    private final Map<String, SplitStrategy> strategyMap;

    public ExpenseService(ExpenseRepository expenseRepo, ExpenseGroupRepository groupRepo, SplitRepository splitRepo, UserRepository userRepo, Map<String, SplitStrategy> strategyMap) {
        this.expenseRepo = expenseRepo;
        this.groupRepo = groupRepo;
        this.splitRepo = splitRepo;
        this.userRepo = userRepo;
        this.strategyMap = strategyMap;
    }

    /**
     * Create an expense with split logic based on the given strategy type.
     */
    public Expense addExpenseWithStrategy(
            Expense expense,
            Map<Long, Double> paidMap,
            Map<Long, Double> percentageMap
    ) {
        List<User> users;

        // 1. Validate & extract participants
        if (expense.getGroup() != null && expense.getGroup() != null) {
            Expense finalExpense = expense;
            ExpenseGroup group = groupRepo.findById(expense.getGroup().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Group not found with ID: " + finalExpense.getGroup().getId()));

            users = group.getMembers();
            expense.setGroup(group); // assign managed group entity to expense

        } else if (expense.getUserIds() != null && !expense.getUserIds().isEmpty()) {
            users = userRepo.findAllById(expense.getUserIds());

        } else {
            throw new IllegalArgumentException("Expense must specify either a valid group or a list of userIds.");
        }

        // 2. Set creation time (in case @CreationTimestamp isn't working)
        expense.setCreatedAt(Date.from(java.time.LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));

        // 3. Save base expense object first
        expense = expenseRepo.save(expense);

        // 4. Pick strategy dynamically
        SplitStrategy strategy = strategyMap.get(expense.getSplitType().name());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported split type: " + expense.getSplitType());
        }

        // 5. Generate and persist splits
        List<Split> splits = strategy.calculateSplits(expense, users, paidMap, percentageMap);
        splitRepo.saveAll(splits);
        expense.setSplits(splits);

        // 6. Return expense with linked splits
        return expense;
    }


    public List<Expense> getUserExpenses(Long userId) {
        return expenseRepo.findAll().stream()
                .filter(exp -> exp.getSplits().stream()
                        .anyMatch(s -> s.getUser().getId() == userId))
                .collect(Collectors.toList());
    }
}

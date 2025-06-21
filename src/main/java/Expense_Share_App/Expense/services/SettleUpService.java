package Expense_Share_App.Expense.services;

import Expense_Share_App.Expense.dto.BalanceDTO;
import Expense_Share_App.Expense.models.Split;
import Expense_Share_App.Expense.repositories.SplitRepository;
import Expense_Share_App.Expense.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class SettleUpService {

    private final SplitRepository splitRepo;
    private final UserRepository userRepo;

    public SettleUpService(SplitRepository splitRepo, UserRepository userRepo) {
        this.splitRepo = splitRepo;
        this.userRepo = userRepo;
    }

    public List<BalanceDTO> settleUpUser(Long userId) {
        List<Split> splits = splitRepo.findAll().stream()
                .filter(s -> s.getUser().getId()==userId ||
                        s.getExpense().getCreatedBy().getId()==userId)
                .toList();

        return getTransactionsToSettle(splits);
    }

    public List<BalanceDTO> settleUpGroup(Long groupId) {
        List<Split> splits = splitRepo.findAll().stream()
                .filter(s -> s.getExpense().getGroup() != null &&
                        s.getExpense().getGroup().getId()==groupId)
                .toList();

        return getTransactionsToSettle(splits);
    }

    private List<BalanceDTO> getTransactionsToSettle(List<Split> splits) {
        Map<Long, Double> netMap = new HashMap<>();
        for (Split split : splits) {
            Long userId = split.getUser().getId();
            double net = split.getPaid() - split.getOwed();
            netMap.put(userId, netMap.getOrDefault(userId, 0.0) + net);
        }

        PriorityQueue<Map.Entry<Long, Double>> debtors = new PriorityQueue<>(Map.Entry.comparingByValue());
        PriorityQueue<Map.Entry<Long, Double>> creditors = new PriorityQueue<>((a, b) -> Double.compare(b.getValue(), a.getValue()));

        for (Map.Entry<Long, Double> entry : netMap.entrySet()) {
            if (entry.getValue() < 0) debtors.add(entry);
            else if (entry.getValue() > 0) creditors.add(entry);
        }

        List<BalanceDTO> txns = new ArrayList<>();
        while (!debtors.isEmpty() && !creditors.isEmpty()) {
            var debtor = debtors.poll();
            var creditor = creditors.poll();

            double settled = Math.min(-debtor.getValue(), creditor.getValue());
            txns.add(new BalanceDTO(
                    userRepo.findById(debtor.getKey()).orElseThrow(),
                    userRepo.findById(creditor.getKey()).orElseThrow(),
                    settled
            ));

            double remainingDebtor = debtor.getValue() + settled;
            double remainingCreditor = creditor.getValue() - settled;

            if (remainingDebtor < 0) debtors.add(Map.entry(debtor.getKey(), remainingDebtor));
            if (remainingCreditor > 0) creditors.add(Map.entry(creditor.getKey(), remainingCreditor));
        }

        return txns;
    }
}

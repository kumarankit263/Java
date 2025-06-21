package Expense_Share_App.Expense.repositories;

import Expense_Share_App.Expense.models.Split;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitRepository extends JpaRepository<Split, Long> {
}

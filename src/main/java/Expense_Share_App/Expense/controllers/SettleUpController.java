package Expense_Share_App.Expense.controllers;

import Expense_Share_App.Expense.dto.BalanceDTO;
import Expense_Share_App.Expense.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/settleup")
public class SettleUpController {

    @Autowired
    private  SettleUpService settleUpService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BalanceDTO>> settleUpUser(@PathVariable Long userId) {
        return ResponseEntity.ok(settleUpService.settleUpUser(userId));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<BalanceDTO>> settleUpGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(settleUpService.settleUpGroup(groupId));
    }
}

package Expense_Share_App.Expense.controllers;


import Expense_Share_App.Expense.models.ExpenseGroup;
import Expense_Share_App.Expense.services.GroupService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseGroup>createGroup(@RequestParam String name, @RequestParam Long creatorId, @RequestBody List<Long>memberIds){
        return ResponseEntity.ok(groupService.createGroup(name,creatorId,memberIds));
    }

    @DeleteMapping("/{groudId}/remove")
    public ResponseEntity<ExpenseGroup>removeMember(@PathVariable Long groupId,@RequestParam Long creatorId ,@RequestParam Long userId){
        return ResponseEntity.ok(groupService.removeMember(groupId, creatorId, userId));
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<ExpenseGroup> getGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupService.getGroup(groupId));
    }

    @PostMapping("/{groupId}/add")
    public ResponseEntity<ExpenseGroup> addMember(@PathVariable Long groupId,
                                                  @RequestParam Long creatorId,
                                                  @RequestParam Long userId) {
        return ResponseEntity.ok(groupService.addMember(groupId, creatorId, userId));
    }
}

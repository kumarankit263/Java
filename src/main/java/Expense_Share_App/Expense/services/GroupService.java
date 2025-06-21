package Expense_Share_App.Expense.services;

import Expense_Share_App.Expense.models.ExpenseGroup;
import Expense_Share_App.Expense.models.User;
import Expense_Share_App.Expense.repositories.ExpenseGroupRepository;
import Expense_Share_App.Expense.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final ExpenseGroupRepository groupRepo;
    private final UserRepository userRepo;

    public GroupService(UserRepository userRepo, ExpenseGroupRepository groupRepo) {
        this.userRepo = userRepo;
        this.groupRepo = groupRepo;
    }
    public ExpenseGroup createGroup(String name, Long creatorId, List<Long> memberIds){
        User creator = userRepo.findById(creatorId).orElseThrow();
        List<User> members = userRepo.findAllById(memberIds);
        members.add(creator); // Ensure creator is part of the group

        ExpenseGroup group = new ExpenseGroup();
        group.setName(name);
        group.setCreatedBy(creator);
        group.setMembers(members);
        group.setCreatedAt(Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        group.setUpdatedAt(Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        return groupRepo.save(group);
    }

    public ExpenseGroup addMember(Long groupId, Long creatorId, Long userId) {
        ExpenseGroup group = groupRepo.findById(groupId).orElseThrow();
        if (group.getCreatedBy().getId() != creatorId) {
            throw new RuntimeException("Only the group creator can add members.");
        }
        User user = userRepo.findById(userId).orElseThrow();
        group.getMembers().add(user);
        return groupRepo.save(group);
    }

    public ExpenseGroup removeMember(Long groupId, Long creatorId, Long userId) {
        ExpenseGroup group = groupRepo.findById(groupId).orElseThrow();
        if (group.getCreatedBy().getId() != creatorId) {
            throw new RuntimeException("Only the group creator can remove members.");
        }
        group.setMembers(group.getMembers().stream()
                .filter(u -> u.getId() != userId)
                .collect(Collectors.toList()));
        return groupRepo.save(group);
    }

    public ExpenseGroup getGroup(Long groupId) {
        return groupRepo.findById(groupId).orElseThrow();
    }
}

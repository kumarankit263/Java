package Expense_Share_App.Expense.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class ExpenseGroup extends BaseEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name="owner")
    private User owner;

    @ManyToMany
    @JoinTable(
            name="expense_group_members",
            joinColumns=@JoinColumn(name="expense_goup_id"),
            inverseJoinColumns=@JoinColumn(name="member_id")
    )
    private List<User>members;

    @ManyToOne
    @JoinColumn(name="created_by",nullable = false)
    private User createdBy;

    public String getName() {
        return name;
    }

    public ExpenseGroup() {
    }

    public ExpenseGroup(String name, User owner, List<User> members, User createdBy) {
        this.name = name;
        this.owner = owner;
        this.members = members;
        this.createdBy = createdBy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}

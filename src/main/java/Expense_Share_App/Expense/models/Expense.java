package Expense_Share_App.Expense.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity

public class Expense extends BaseEntity{

    private String description;

    @Column(name="total_amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name="split_type")
    private SplitType splitType;

    @ManyToOne
    @JoinColumn(name="created_by_id",nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name="group_id")
    private ExpenseGroup group;

    @OneToMany(mappedBy = "expense",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Split>splits=new ArrayList<>();

    @Transient
    private List<Long>userIds;

    public Expense() {
    }

    public Expense(String description, Double totalAmount, SplitType splitType, User createdBy, ExpenseGroup group, List<Split> splits, List<Long> userIds) {
        this.description = description;
        this.totalAmount = totalAmount;
        this.splitType = splitType;
        this.createdBy = createdBy;
        this.group = group;
        this.splits = splits;
        this.userIds = userIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public ExpenseGroup getGroup() {
        return group;
    }

    public void setGroup(ExpenseGroup group) {
        this.group = group;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }
}

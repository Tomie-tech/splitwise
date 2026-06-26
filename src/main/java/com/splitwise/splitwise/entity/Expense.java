package com.splitwise.splitwise.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<ExpenseSplit> splits;

    public Expense(){}
    //Constructor with description, amount, paidBy, group

    public Expense(String description, double amount,User paidBy,Group group){
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.group = group;
        this.splits = new ArrayList<>();
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public User getPaidBy() { return paidBy; }
    public Group getgroup() { return group; }
    public void addSplit(ExpenseSplit split){
        splits.add(split);
    }
    public double getAmount() { return amount; }
    public List<ExpenseSplit> getSplits() { return splits; }
    public void setId(Long id) {
        this.id = id;
    }

}

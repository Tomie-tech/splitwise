package com.splitwise.splitwise.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "expense_splits")
public class ExpenseSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private boolean isPaid = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    public ExpenseSplit(){}
    public ExpenseSplit(double amount , User user, Expense expense){
        this.amount = amount;
        this.user =  user;
        this.expense = expense;
    }

    public Long getId() { return id; }
    public double getAmount() { return amount; }
    public User getUser() { return user; }
    public Expense getExpense() { return expense; }

    public boolean isPaid() {
        return isPaid;
    }
    public void settle(){
        isPaid = true;
    }
}


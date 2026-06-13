package com.splitwise.splitwise.dto.response;
import java.util.*;

public class ExpenseResponse {
    private Long id;
    private String description;
    private double amount;
    private UserSummary paidBy;
    private Long groupId;

    public ExpenseResponse(Long id, String description, double amount, UserSummary paidBy, Long groupId){
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.groupId = groupId;
    }
    public Long getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount(){return amount;}
    public UserSummary getPaidBy(){return paidBy;}
    public Long getGroupId(){return groupId;}
}

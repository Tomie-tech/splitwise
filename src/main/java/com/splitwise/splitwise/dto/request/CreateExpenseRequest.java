package com.splitwise.splitwise.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.*;

public class CreateExpenseRequest {
    /*
    description (String, NotBlank)
amount (double)
groupId (Long) — which group
splitAmongIds (List<Long>) — user ids to split among
empty constructor, getters, setters
     */
    @NotBlank
    private String description;
    private double amount;
    private long groupId;
    private List<Long> splitAmongIds;

    public CreateExpenseRequest(){}

    public CreateExpenseRequest(String description, double amount, long groupId,List<Long> splitAmongIds){
        this.description = description;
        this.amount = amount;
        this.groupId = groupId;
        this.splitAmongIds = splitAmongIds;
    }

    public String getDescription(){
        return description;
    }
    public double getAmount(){
        return amount;
    }
    public long getGroupId(){
        return groupId;
    }
    public List<Long> getSplitAmongIds(){
        return splitAmongIds;
    }
    public void setDescription(String description) { this.description = description; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setGroupId(long groupId) { this.groupId = groupId; }
    public void setSplitAmongIds(List<Long> splitAmongIds) { this.splitAmongIds = splitAmongIds; }


}

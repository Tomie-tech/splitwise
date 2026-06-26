package com.splitwise.splitwise.service;

import com.splitwise.splitwise.dto.request.CreateExpenseRequest;
import com.splitwise.splitwise.dto.response.ExpenseResponse;
import com.splitwise.splitwise.dto.response.UserSummary;
import com.splitwise.splitwise.entity.Expense;
import com.splitwise.splitwise.entity.ExpenseSplit;
import com.splitwise.splitwise.entity.Group;
import com.splitwise.splitwise.entity.User;
import com.splitwise.splitwise.repository.ExpenseRepository;
import com.splitwise.splitwise.repository.ExpenseSplitRepository;
import com.splitwise.splitwise.repository.GroupRepository;
import com.splitwise.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;

    public ExpenseResponse addExpense(CreateExpenseRequest request, User currentUser) {

        Long id = request.getGroupId();
        Group group1 = groupRepository.findById(id)
            .orElseThrow();

        Expense expense = new Expense(request.getDescription(), request.getAmount(), currentUser,group1);

        double splitAmount = request.getAmount()/request.getSplitAmongIds().size();
        for(long memberId : request.getSplitAmongIds()) {
            Optional<User> user = userRepository.findById(memberId);
            if(user.isPresent()) {
                ExpenseSplit split = new ExpenseSplit(splitAmount, user.get(), expense);
                expense.addSplit(split);
            }
        }
        Expense saved = expenseRepository.save(expense);
        return toResponse(saved);
    }

    public List<ExpenseResponse> getGroupExpenses(Long groupId) {
        // find all expenses for group
        // convert to ExpenseResponse list
        return expenseRepository.findByGroupId(groupId).stream()
                .map(expense -> toResponse(expense)).toList();
    }

    private ExpenseResponse toResponse(Expense expense) {
        UserSummary paidBy = new UserSummary(
                expense.getPaidBy().getId(),
                expense.getPaidBy().getEmail()
        );
        return new ExpenseResponse(
                expense.getId(),
                expense.getDescription(),
                expense.getAmount(),
                paidBy,
                expense.getgroup().getId()
        );
    }
    public String settleSplit(Long splitId) {

        ExpenseSplit split = expenseSplitRepository.findById(splitId).orElseThrow();
        split.settle();
        expenseSplitRepository.save(split);
        return "Split settled successfully";
    }
}
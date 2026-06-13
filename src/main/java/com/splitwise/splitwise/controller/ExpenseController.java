package com.splitwise.splitwise.controller;

import com.splitwise.splitwise.dto.request.CreateExpenseRequest;
import com.splitwise.splitwise.dto.response.ExpenseResponse;
import com.splitwise.splitwise.dto.response.GroupResponse;
import com.splitwise.splitwise.entity.Group;
import com.splitwise.splitwise.entity.User;
import com.splitwise.splitwise.repository.GroupRepository;
import com.splitwise.splitwise.repository.UserRepository;
import com.splitwise.splitwise.service.ExpenseService;
import com.splitwise.splitwise.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ExpenseResponse addExpense(@Valid @RequestBody CreateExpenseRequest request){
        User currentUser = userRepository.findByEmail(getCurrentUserEmail()).orElseThrow();
        return expenseService.addExpense(request,currentUser);
    }

    //GET /expenses/group/{groupId} → getGroupExpenses
    // ✅ groupId comes from URL, pass it directly to service
    @GetMapping("/group/{groupId}")
    public List<ExpenseResponse> getGroupExpenses(@PathVariable Long groupId){
        return expenseService.getGroupExpenses(groupId);
    }
    private String getCurrentUserEmail() {
        return (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
    @PutMapping("/splits/{splitId}/settle")
    public String settleSplit(@PathVariable Long splitId) {
        return expenseService.settleSplit(splitId);
    }
}

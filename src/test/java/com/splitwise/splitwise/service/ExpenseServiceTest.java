package com.splitwise.splitwise.service;

import com.splitwise.splitwise.entity.ExpenseSplit;

import com.splitwise.splitwise.dto.request.CreateExpenseRequest;
import com.splitwise.splitwise.entity.Expense;
import com.splitwise.splitwise.entity.Group;
import com.splitwise.splitwise.entity.User;
import com.splitwise.splitwise.repository.ExpenseRepository;
import com.splitwise.splitwise.repository.ExpenseSplitRepository;
import com.splitwise.splitwise.repository.GroupRepository;
import com.splitwise.splitwise.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.splitwise.splitwise.dto.response.ExpenseResponse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private ExpenseSplitRepository expenseSplitRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void testAddExpense() {

        User currentUser = new User("test@gmail.com", "password");
        currentUser.setId(1L);

        Group savedGroup = new Group("Test Group", currentUser);
        savedGroup.setId(1L);

        when(groupRepository.findById(1L)).thenReturn(Optional.of(savedGroup));

        CreateExpenseRequest request = new CreateExpenseRequest();
        request.setDescription("Dinner");
        request.setAmount(100.0);
        request.setGroupId(1L);
        List<Long> list = new ArrayList<>();
        list.add(currentUser.getId());
        request.setSplitAmongIds(list);
        when(userRepository.findById(1L)).thenReturn(Optional.of(currentUser));
        Expense savedExpense = new Expense("Dinner", 100.0, currentUser, savedGroup);
        savedExpense.setId(5L);
        when(expenseRepository.save(any(Expense.class))).thenReturn(savedExpense);

        ExpenseResponse response = expenseService.addExpense(request, currentUser);
        assertNotNull(response);
        assertEquals("Dinner", response.getDescription());
        assertEquals(5L, response.getId());
        assertEquals(100.0,response.getAmount(),0.001);
    }

    @Test
    void testSettleSplit() {

        ExpenseSplit split = new ExpenseSplit(100.0, null, null);
        split.setId(1L);
        when(expenseSplitRepository.findById(1L)).thenReturn(Optional.of(split));
        String result = expenseService.settleSplit(1L);
        assertEquals("Split settled successfully", result);
        assertEquals(true, split.isPaid());

    }
}

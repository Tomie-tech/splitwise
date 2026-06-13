package com.splitwise.splitwise.repository;

import com.splitwise.splitwise.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    List<Expense> findByGroupId(Long groupId);
}

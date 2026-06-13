package com.splitwise.splitwise.repository;

import com.splitwise.splitwise.entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long>{

    List<ExpenseSplit> findByUserIdAndIsPaidFalse(Long userId);
}

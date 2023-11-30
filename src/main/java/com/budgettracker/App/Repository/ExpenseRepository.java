package com.budgettracker.App.Repository;

import com.budgettracker.App.Entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expenses,Long> {
    List<Expenses> findByUserId(Long userId);
    List<Expenses> findByDateBetweenAndUserId(LocalDateTime startDate, LocalDateTime enDate,Long userId);
}

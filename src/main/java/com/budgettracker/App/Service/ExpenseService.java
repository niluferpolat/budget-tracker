package com.budgettracker.App.Service;

import com.budgettracker.App.Dto.ExpenseDto;
import com.budgettracker.App.Dto.ExpenseUpdateDto;
import com.budgettracker.App.Entity.Expenses;
import com.budgettracker.App.Repository.ExpenseRepository;
import com.budgettracker.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository repository;
    @Autowired
    UserRepository userRepository;

    public List<Expenses> getAllExpensesByUserId(Long userId){
        return repository.findByUserId(userId);
    }
    public Expenses update(ExpenseUpdateDto expenseUpdateDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        Optional<Expenses> expense1=repository.findById(expenseUpdateDto.getId());
        if(!expense1.isPresent()){
            throw new IllegalArgumentException("No expense found!");
        }
        Expenses expenseToUpdate=expense1.get();
        expenseToUpdate.setDate(LocalDateTime.parse(formattedDateTime,formatter));
        expenseToUpdate.setType(expenseUpdateDto.getType());
        expenseToUpdate.setMoneyAmount(expenseUpdateDto.getMoneyAmount());
        repository.save(expenseToUpdate);
        return expenseToUpdate;

    }
    public Expenses add(ExpenseDto expenseDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        Expenses expenses=new Expenses();
        expenses.setDate(LocalDateTime.parse(formattedDateTime,formatter));
        expenses.setType(expenseDto.getType());
        expenses.setUser(userRepository.findById(expenseDto.getUserId()).get());
        expenses.setMoneyAmount(expenseDto.getMoneyAmount());
        repository.save(expenses);
        return expenses;
    }
    public List<Expenses> search(LocalDateTime startDate, LocalDateTime endDate,Long userId){
        return  repository.findByDateBetweenAndUserId(startDate,endDate,userId);
    }
}

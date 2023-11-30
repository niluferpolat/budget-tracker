package com.budgettracker.App.Controller;

import com.budgettracker.App.Dto.ExpenseDto;
import com.budgettracker.App.Dto.ExpenseUpdateDto;
import com.budgettracker.App.Entity.Expenses;
import com.budgettracker.App.Entity.User;
import com.budgettracker.App.Repository.ExpenseRepository;
import com.budgettracker.App.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;
    @Autowired
    ExpenseRepository expenseRepository;
    @GetMapping("/getExpensesByUser")
    @ResponseBody
    public List<Expenses> getExpensesByUser(Long userId){
        return expenseService.getAllExpensesByUserId(userId);
    }
    @PostMapping("/addExpense")
    @ResponseBody
    public Expenses addExpense(@RequestBody ExpenseDto expenseDto){
        return expenseService.add(expenseDto);
    }
    @PutMapping("/updateService")
    @ResponseBody
    public Expenses updateExpense(@RequestBody ExpenseUpdateDto expenseUpdateDto){
      return  expenseService.update(expenseUpdateDto);
    }

    @PostMapping("/searchExpense")
    @ResponseBody
    public List<Expenses> searchExpenses(@RequestParam String startDate, @RequestParam String endDate, @RequestParam Long userId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
        return  expenseService.search(startDateTime,endDateTime,userId);
    }


}

package com.budgettracker.App.Dto;

import com.budgettracker.App.Entity.User;
import lombok.Data;

@Data
public class ExpenseUpdateDto {
    private Long id;
    private double moneyAmount;
    private String type;
}

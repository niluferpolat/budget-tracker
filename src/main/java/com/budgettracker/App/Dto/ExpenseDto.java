package com.budgettracker.App.Dto;

import com.budgettracker.App.Entity.User;
import lombok.Data;

@Data
public class ExpenseDto {
    private double moneyAmount;
    private String type;
    private Long userId;
}

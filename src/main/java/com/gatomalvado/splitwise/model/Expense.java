package com.gatomalvado.splitwise.model;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {
    private ExpenseType expenseType;
    private String paidBy;
    private List<String> paidFor;
    private Double amount;
    private List<Double> percentage;
    private List<Double> exactAmount;
}

package com.gatomalvado.splitwise.service;

import java.util.List;

import com.gatomalvado.splitwise.model.Expense;
import com.gatomalvado.splitwise.model.ExpenseType;
import com.gatomalvado.splitwise.model.UserBalance;

public interface UserBalanceGenerator {
    List<UserBalance> generateUserBalance(Expense expense);

    ExpenseType getType();
}

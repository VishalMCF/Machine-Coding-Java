package com.gatomalvado.done.splitwise.service;

import java.util.List;

import com.gatomalvado.done.splitwise.model.Expense;
import com.gatomalvado.done.splitwise.model.ExpenseType;
import com.gatomalvado.done.splitwise.model.UserBalance;

public interface UserBalanceGenerator {
    List<UserBalance> generateUserBalance(Expense expense);

    ExpenseType getType();
}

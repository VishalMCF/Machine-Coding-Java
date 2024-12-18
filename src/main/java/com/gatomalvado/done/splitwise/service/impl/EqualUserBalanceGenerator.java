package com.gatomalvado.done.splitwise.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.gatomalvado.done.splitwise.model.Expense;
import com.gatomalvado.done.splitwise.model.ExpenseType;
import com.gatomalvado.done.splitwise.model.UserBalance;
import com.gatomalvado.done.splitwise.service.UserBalanceGenerator;

public class EqualUserBalanceGenerator implements UserBalanceGenerator {

    @Override
    public List<UserBalance> generateUserBalance(Expense expense) {
        List<UserBalance> userBalances = new ArrayList<>();
        Double amount = expense.getAmount();
        int usersLength = expense.getPaidFor().size();
        double amountToDistribute = amount/usersLength;
        for(String userId: expense.getPaidFor()) {
            if(!userId.equals(expense.getPaidBy())){
                userBalances.add(UserBalance.builder().amount(amountToDistribute).owedTo(expense.getPaidBy()).userId(userId).build());
            }
        }
        return userBalances;
    }

    @Override
    public ExpenseType getType() {
        return ExpenseType.EQUAL;
    }

}

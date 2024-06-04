package com.gatomalvado.splitwise.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.gatomalvado.splitwise.model.Expense;
import com.gatomalvado.splitwise.model.ExpenseType;
import com.gatomalvado.splitwise.model.UserBalance;
import com.gatomalvado.splitwise.service.UserBalanceGenerator;

public class ExactUserBalanceGenerator implements UserBalanceGenerator {

    @Override
    public List<UserBalance> generateUserBalance(Expense expense) {
        if(!validateShare(expense)) {
            // throw exception here
        }
        List<UserBalance> userBalances = new ArrayList<>();
        int usersLength = expense.getPaidFor().size();
        List<Double> amounts = expense.getExactAmount();
        for(int i=0; i<usersLength; i++){
            userBalances.add(UserBalance.builder()
                .owedTo(expense.getPaidBy())
                .amount(amounts.get(i))
                .userId(expense.getPaidFor().get(i))
                .build());
        }
        return userBalances;
    }

    @Override
    public ExpenseType getType() {
        return ExpenseType.EXACT;
    }

    private boolean validateShare(Expense expense) {
        List<Double> amounts = expense.getExactAmount();
        Double amount = expense.getAmount();
        double total = 0.0;
        for(int i=0; i<amounts.size(); i++){
            total += amounts.get(i);
        }
        return amount==total;
    }
}

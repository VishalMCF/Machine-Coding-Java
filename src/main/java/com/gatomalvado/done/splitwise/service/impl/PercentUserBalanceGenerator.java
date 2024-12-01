package com.gatomalvado.done.splitwise.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.gatomalvado.done.splitwise.model.Expense;
import com.gatomalvado.done.splitwise.model.ExpenseType;
import com.gatomalvado.done.splitwise.model.UserBalance;
import com.gatomalvado.done.splitwise.service.UserBalanceGenerator;

public class PercentUserBalanceGenerator implements UserBalanceGenerator {

    @Override
    public List<UserBalance> generateUserBalance(Expense expense) {
        if(!validateShare(expense)) {
            // throw exception here
        }
        List<UserBalance> userBalances = new ArrayList<>();
        Double amount = expense.getAmount();
        int usersLength = expense.getPaidFor().size();
        for(int i=0; i<usersLength; i++){
            if(expense.getPaidFor().get(i).equals(expense.getPaidBy())){
                continue;
            }
            userBalances.add(UserBalance.builder()
                .owedTo(expense.getPaidBy())
                .amount((amount*expense.getPercentage().get(i))/100.0)
                .userId(expense.getPaidFor().get(i))
                .build());
        }
        return userBalances;
    }

    @Override
    public ExpenseType getType() {
        return ExpenseType.PERCENT;
    }

    private boolean validateShare(Expense expense) {
        List<Double> percentage = expense.getPercentage();
        AtomicReference<Double> ans = new AtomicReference<>(0.0);
        percentage.stream().forEach((pc) ->{
            ans.set(ans.get() + pc);
        });
        Double finalValue = ans.get();
        return finalValue == 100.0;
    }

}

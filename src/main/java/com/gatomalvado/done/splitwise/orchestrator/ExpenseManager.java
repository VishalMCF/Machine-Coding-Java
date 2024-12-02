package com.gatomalvado.done.splitwise.orchestrator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gatomalvado.done.splitwise.model.Expense;
import com.gatomalvado.done.splitwise.model.ExpenseType;
import com.gatomalvado.done.splitwise.model.User;
import com.gatomalvado.done.splitwise.model.UserBalance;
import com.gatomalvado.done.splitwise.service.StrategyConfig;
import com.gatomalvado.done.splitwise.service.UserBalanceGenerator;

public class ExpenseManager {

    private Map<String, User> userStore;

    private List<UserBalance> userBalances;

    private Map<ExpenseType, UserBalanceGenerator> userBalanceGeneratorMap;

    private StrategyConfig strategyConfig;

    public ExpenseManager() {
        this.userStore = new HashMap<>();
        this.userBalances = new ArrayList<>();
        this.strategyConfig = StrategyConfig.getInstance();
        initMap();
    }

    public void addExpense(Expense expense) {
        List<UserBalance> userBalanceList = userBalanceGeneratorMap.get(expense.getExpenseType()).generateUserBalance(expense);
        userBalances.addAll(userBalanceList);
    }

    public void addUser(User user) {
        userStore.put(user.getUserId(), user);
    }

    public void showBalance() {
        Collection<User> userCollection = userStore.values();
        userCollection.stream().forEach((usr) -> {
            List<UserBalance> userBalanceList = userBalances.stream().filter((usb) -> usb.getUserId().equals(usr.getUserId())).toList();
            Map<String, Double> oweToMap = new HashMap<>();
            userBalanceList.forEach((usb) -> {
                oweToMap.put(usb.getOwedTo(), oweToMap.getOrDefault(usb.getOwedTo(), 0.0) + usb.getAmount());
            });
            oweToMap.keySet().forEach((otm) -> {
                System.out.println(usr.getUserId() + " owes to " + otm + " " + oweToMap.get(otm));
            });
        });
    }

    public void showBalanceForUser(String userId) {
        List<UserBalance> userBalanceList = userBalances.stream().filter((usb) -> usb.getUserId().equals(userId)).toList();
        Map<String, Double> oweToMap = new HashMap<>();
        userBalanceList.forEach((usb) -> {
            oweToMap.put(usb.getOwedTo(), oweToMap.getOrDefault(usb.getOwedTo(), 0.0) + usb.getAmount());
        });
        oweToMap.keySet().forEach((otm) -> {
            System.out.println(userId + " owes to " + otm + " " + oweToMap.get(otm));
        });
    }

    private void initMap() {
        userBalanceGeneratorMap = new HashMap<>();
        for (UserBalanceGenerator userBalanceGenerator : strategyConfig.getStrategies()) {
            userBalanceGeneratorMap.put(userBalanceGenerator.getType(), userBalanceGenerator);
        }
    }

}

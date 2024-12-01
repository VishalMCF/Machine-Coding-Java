package com.gatomalvado.done.splitwise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gatomalvado.done.splitwise.model.Expense;
import com.gatomalvado.done.splitwise.model.ExpenseType;
import com.gatomalvado.done.splitwise.model.User;
import com.gatomalvado.done.splitwise.orchestrator.ExpenseManager;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager expenseManager = new ExpenseManager();

        // create users
        User user1 = User.builder().userId("User1").build();
        User user2 = User.builder().userId("User2").build();
        User user3 = User.builder().userId("User3").build();
        User user4 = User.builder().userId("User4").build();

        // add user to the expense manager
        expenseManager.addUser(user1);
        expenseManager.addUser(user2);
        expenseManager.addUser(user3);
        expenseManager.addUser(user4);

        while(true){
            String command    = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch(commandType){
                case "SHOW":
                    if(commands.length == 2){
                        expenseManager.showBalanceForUser(commands[1]);
                    } else {
                        expenseManager.showBalance();
                    }
                    continue;
                case "EXPENSE":
                    String userWhoPaid = commands[1];
                    int numberOfUsers = Integer.parseInt(commands[2]);
                    String expenseType = commands[3 + numberOfUsers];
                    List<String> userInvolved = new ArrayList<>();
                    for(int i=3; i< 3+numberOfUsers; i++){
                        userInvolved.add(commands[i]);
                    }
                    Double amount = Double.parseDouble(commands[4+numberOfUsers]);
                    Expense expense = null;
                    switch(expenseType) {
                        case "EQUAL":
                            expense = Expense.builder()
                                .expenseType(ExpenseType.EQUAL)
                                .paidBy(userWhoPaid)
                                .paidFor(userInvolved)
                                .amount(amount)
                                .build();
                            expenseManager.addExpense(expense);
                            continue;
                        case "EXACT":
                            List<Double> amounts = new ArrayList<>();
                            for(int i=5+numberOfUsers; i<commands.length; i++){
                                amounts.add(Double.parseDouble(commands[i]));
                            }
                            expense = Expense.builder()
                                .expenseType(ExpenseType.EXACT)
                                .paidBy(userWhoPaid)
                                .paidFor(userInvolved)
                                .amount(amount)
                                .exactAmount(amounts)
                                .build();
                            expenseManager.addExpense(expense);
                            continue;
                        case "PERCENT":
                            List<Double> percentages = new ArrayList<>();
                            for(int i=5+numberOfUsers; i<commands.length; i++){
                                percentages.add(Double.parseDouble(commands[i]));
                            }
                            expense = Expense.builder()
                                .expenseType(ExpenseType.PERCENT)
                                .paidBy(userWhoPaid)
                                .paidFor(userInvolved)
                                .amount(amount)
                                .percentage(percentages)
                                .build();
                            expenseManager.addExpense(expense);
                            continue;
                        default:
                            // throw exception
                            System.out.println("Command not supported ");
                            break;
                    }
                default:
                    break;
            }
        }
    }



}

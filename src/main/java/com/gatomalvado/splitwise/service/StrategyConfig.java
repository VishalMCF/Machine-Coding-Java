package com.gatomalvado.splitwise.service;

import java.util.List;

import com.gatomalvado.splitwise.service.impl.EqualUserBalanceGenerator;
import com.gatomalvado.splitwise.service.impl.ExactUserBalanceGenerator;
import com.gatomalvado.splitwise.service.impl.PercentUserBalanceGenerator;

public class StrategyConfig {

    private static final StrategyConfig INSTANCE = new StrategyConfig();

    private List<UserBalanceGenerator> strategies;

    private StrategyConfig(){
        strategies = List.of(new EqualUserBalanceGenerator(), new ExactUserBalanceGenerator(), new PercentUserBalanceGenerator());
    }

    public List<UserBalanceGenerator> getStrategies(){
        return strategies;
    }

    public static StrategyConfig getInstance(){
        return INSTANCE;
    }

}

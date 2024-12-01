package com.gatomalvado.done.snakesandladders.service;

import java.util.Random;
import java.util.random.RandomGenerator;

public class SingleDiceNextPositionStrategy implements NextPositionStrategy {

    @Override
    public int rollDice() {
        return Random.from(RandomGenerator.getDefault()).nextInt(1,7);
    }
}

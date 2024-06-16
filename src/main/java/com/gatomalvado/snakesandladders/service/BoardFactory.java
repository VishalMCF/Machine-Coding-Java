package com.gatomalvado.snakesandladders.service;


import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;

@Getter
public class BoardFactory {


    private NextPositionStrategy nextPositionStrategy;


    private WinnerDeciderStrategy winnerDeciderStrategy;


    private BoardGenerator boardGenerator;

    public BoardFactory(int noOfDice, int noOfWinners, boolean autoGenerate) {
        synchronized (this) {
            initNextPositionStrategy(noOfDice);
            initWinnerDeciderStrategy(noOfWinners);
            initBoardGenerator(autoGenerate);
        }
    }

    private void initBoardGenerator(boolean autoGenerate) {
        if(autoGenerate){
            throw new UnsupportedOperationException("Auto generation of snakes and ladders is not supported");
        }
        this.boardGenerator = new ManualBoardGenerator();
    }

    private synchronized void initWinnerDeciderStrategy(int noOfWinners) {
        switch (noOfWinners) {
            case 1:
                this.winnerDeciderStrategy = new SingleWinnerStrategy();
                return;
            default:
                throw new UnsupportedOperationException("Only single winner is supported as of now");
        }
    }

    private synchronized void initNextPositionStrategy(int noOfDice) {
        switch (noOfDice) {
            case 1:
                this.nextPositionStrategy = new SingleDiceNextPositionStrategy();
                return;
            default:
                throw new UnsupportedOperationException("Only single dice is supported as of now");
        }
    }

}

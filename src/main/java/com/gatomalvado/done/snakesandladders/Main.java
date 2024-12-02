package com.gatomalvado.done.snakesandladders;

import com.gatomalvado.done.snakesandladders.orchestrator.GameOrchestrator;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GameOrchestrator gameOrchestrator = new GameOrchestrator(1, 1, false, 100);
        gameOrchestrator.takeSnakeInput();
        gameOrchestrator.takeLadderInput();
        gameOrchestrator.takePlayerInput();
        while(!gameOrchestrator.anyWinner()){
            gameOrchestrator.takeTurn();
            Thread.sleep(2000);
        }
    }
}

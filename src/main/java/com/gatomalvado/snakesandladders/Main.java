package com.gatomalvado.snakesandladders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.gatomalvado.snakesandladders.model.Ladder;
import com.gatomalvado.snakesandladders.model.Player;
import com.gatomalvado.snakesandladders.model.Snake;
import com.gatomalvado.snakesandladders.orchestrator.GameOrchestrator;

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

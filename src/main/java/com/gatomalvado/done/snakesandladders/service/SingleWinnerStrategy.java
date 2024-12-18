package com.gatomalvado.done.snakesandladders.service;

import java.util.List;
import java.util.Queue;

import com.gatomalvado.done.snakesandladders.model.Player;

public class SingleWinnerStrategy implements WinnerDeciderStrategy {

    @Override
    public boolean closeGame(Queue<Player> players, int maxPos) {
        List<Player> winner = players.stream().filter((p) -> p.getCurrPos() >= maxPos).toList();
        if(winner.size() ==  1){
            System.out.println(winner.get(0).getName()+" won the game....");
            return true;
        }
        return false;
    }
}

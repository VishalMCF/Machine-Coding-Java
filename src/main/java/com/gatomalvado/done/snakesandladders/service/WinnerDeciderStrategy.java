package com.gatomalvado.done.snakesandladders.service;

import java.util.Queue;

import com.gatomalvado.done.snakesandladders.model.Player;

public interface WinnerDeciderStrategy {

    boolean closeGame(Queue<Player> players, int maxPos);

}

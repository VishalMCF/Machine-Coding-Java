package com.gatomalvado.snakesandladders.service;

import java.util.List;
import java.util.Queue;

import com.gatomalvado.snakesandladders.model.Player;

public interface WinnerDeciderStrategy {

    boolean closeGame(Queue<Player> players, int maxPos);

}

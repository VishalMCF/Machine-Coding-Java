package com.gatomalvado.done.snakesandladders.service;

import java.util.List;
import java.util.Queue;

import com.gatomalvado.done.snakesandladders.model.Ladder;
import com.gatomalvado.done.snakesandladders.model.Player;
import com.gatomalvado.done.snakesandladders.model.Snake;

public interface BoardGenerator{

    Queue<Player> takePlayerInput();

    List<Snake> takeSnakeInput();

    List<Ladder> takeLadderInput();

}

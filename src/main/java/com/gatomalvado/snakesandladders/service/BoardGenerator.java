package com.gatomalvado.snakesandladders.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import com.gatomalvado.snakesandladders.model.Ladder;
import com.gatomalvado.snakesandladders.model.Player;
import com.gatomalvado.snakesandladders.model.Snake;

public interface BoardGenerator{

    Queue<Player> takePlayerInput();

    List<Snake> takeSnakeInput();

    List<Ladder> takeLadderInput();

}

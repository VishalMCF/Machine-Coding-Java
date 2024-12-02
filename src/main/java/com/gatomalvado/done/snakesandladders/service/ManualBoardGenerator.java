package com.gatomalvado.done.snakesandladders.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import com.gatomalvado.done.snakesandladders.model.Ladder;
import com.gatomalvado.done.snakesandladders.model.Player;
import com.gatomalvado.done.snakesandladders.model.Snake;

public class ManualBoardGenerator implements BoardGenerator {

    private Scanner scanner;
    private Set<String> positionSet;

    public ManualBoardGenerator(){
        this.scanner = new Scanner(System.in);
        this.positionSet = new HashSet<>();
    }

    @Override
    public Queue<Player> takePlayerInput() {
        Queue<Player> players = new LinkedList<>();
        int noOfPlayers = scanner.nextInt();
        scanner.nextLine();
        for(int i=0; i<noOfPlayers; i++){
            String playerName = scanner.nextLine();
            players.add(new Player(playerName, 0));
        }
        return players;
    }

    @Override
    public List<Snake> takeSnakeInput() {
        List<Snake> snakes = new ArrayList<>();
        int noOfSnakes = scanner.nextInt();
        scanner.nextLine();
        for(int i=0; i<noOfSnakes; i++){
            String snakePosInput = scanner.nextLine();
            if(positionSet.contains(snakePosInput)){
                System.out.println("Position is already occupied by another snake or ladder");
                i--;
                continue;
            }
            positionSet.add(snakePosInput);
            String[] positions = snakePosInput.split(" ");
            try{
                int xPos = Integer.parseInt(positions[0]);
                int yPos = Integer.parseInt(positions[1]);
                String snakeId = "snake-"+(i+1);
                snakes.add(new Snake(snakeId, xPos, yPos));
            }catch(Exception e){
                System.out.println("Wrong input entered. Please try again..");
                i--;
            }
        }
        return snakes;
    }

    @Override
    public List<Ladder> takeLadderInput() {
        List<Ladder> ladders = new ArrayList<>();
        int noOfLadders = scanner.nextInt();
        scanner.nextLine();
        for(int i=0; i<noOfLadders; i++){
            String ladderPosInput = scanner.nextLine();
            if(positionSet.contains(ladderPosInput)){
                System.out.println("Position is already occupied by another snake or ladder");
                i--;
                continue;
            }
            positionSet.add(ladderPosInput);
            String[] positions = ladderPosInput.split(" ");
            try{
                int xPos = Integer.parseInt(positions[0]);
                int yPos = Integer.parseInt(positions[1]);
                String ladderId = "ladder-"+(i+1);
                ladders.add(new Ladder(ladderId, xPos, yPos));
            }catch(Exception e){
                System.out.println("Wrong input entered. Please try again..");
                i--;
            }
        }
        return ladders;
    }
}

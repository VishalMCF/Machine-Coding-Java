package com.gatomalvado.done.snakesandladders.orchestrator;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

import com.gatomalvado.done.snakesandladders.model.Ladder;
import com.gatomalvado.done.snakesandladders.model.Player;
import com.gatomalvado.done.snakesandladders.model.Snake;
import com.gatomalvado.done.snakesandladders.service.BoardFactory;
import com.gatomalvado.done.snakesandladders.service.BoardGenerator;
import com.gatomalvado.done.snakesandladders.service.NextPositionStrategy;
import com.gatomalvado.done.snakesandladders.service.WinnerDeciderStrategy;

public class GameOrchestrator {

    private Queue<Player> players;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private final int boardLength;
    private final NextPositionStrategy nextPositionStrategy;
    private final WinnerDeciderStrategy winnerDeciderStrategy;
    private final BoardGenerator boardGenerator;

    public GameOrchestrator(int noOfDice, int noOfWinners, boolean autoGenerate, int boardLength) {
        synchronized (this) {
            BoardFactory boardFactory = new BoardFactory(noOfDice, noOfWinners, autoGenerate);
            this.nextPositionStrategy = boardFactory.getNextPositionStrategy();
            this.winnerDeciderStrategy = boardFactory.getWinnerDeciderStrategy();
            this.boardGenerator = boardFactory.getBoardGenerator();
            this.boardLength = boardLength;
        }
    }

    public synchronized void takeSnakeInput() {
        this.snakes = this.boardGenerator.takeSnakeInput();
    }

    public synchronized void takeLadderInput() {
        this.ladders = this.boardGenerator.takeLadderInput();
    }

    public synchronized void takePlayerInput() {
        this.players = this.boardGenerator.takePlayerInput();
    }

    public boolean anyWinner() {
        return this.winnerDeciderStrategy.closeGame(this.players, this.boardLength);
    }

    public void takeTurn() {
        Player currPlayer = players.remove();
        this.players.add(currPlayer);
        int rolledValue = this.nextPositionStrategy.rollDice();
        int newPosition = currPlayer.getCurrPos() + rolledValue;
        Optional<Snake> snake = checkForSnake(newPosition);
        if (snake.isPresent()) {
            int snakeFinalPos = snake.get().getEndPos();
            System.out.println(
                "Player " + currPlayer.getName() + " got bit by snake at " + newPosition + " and moved from " + currPlayer.getCurrPos() + " to "
                    + snakeFinalPos);
            currPlayer.setCurrPos(snakeFinalPos);
            return;
        }
        Optional<Ladder> ladder = checkForLadder(newPosition);
        if (ladder.isPresent()) {
            int ladderFinalPos = ladder.get().getEndPos();
            System.out.println(
                "Player " + currPlayer.getName() + " got supported by a ladder at " + newPosition + " and moved from " + currPlayer.getCurrPos()
                    + " to " + ladderFinalPos);
            currPlayer.setCurrPos(ladderFinalPos);
            return;
        }
        System.out.println("Player " + currPlayer.getName() + " moved from " + currPlayer.getCurrPos() + " to " + newPosition);
        currPlayer.setCurrPos(newPosition);
    }

    private Optional<Snake> checkForSnake(int position) {
        List<Snake> snakes = this.snakes.stream().filter((s) -> s.getStartPos() == position).toList();
        if (snakes.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(snakes.get(0));
    }

    private Optional<Ladder> checkForLadder(int position) {
        List<Ladder> ladders = this.ladders.stream().filter((l) -> l.getStartPos() == position).toList();
        if (ladders.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(ladders.get(0));
    }
}

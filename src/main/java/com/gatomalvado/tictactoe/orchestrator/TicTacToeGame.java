package com.gatomalvado.tictactoe.orchestrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.gatomalvado.tictactoe.model.Board;
import com.gatomalvado.tictactoe.model.Move;
import com.gatomalvado.tictactoe.model.Symbol;
import com.gatomalvado.tictactoe.model.User;
import com.gatomalvado.tictactoe.service.BoardFactory;
import com.gatomalvado.tictactoe.service.StrategyFactory;


public class TicTacToeGame {

    private List<Symbol> symbolList;
    private List<User> users;
    private Board board;
    private Map<String, User> symbolUserMap;
    private Queue<User> turnQueue;
    private List<Move> moves;
    private StrategyFactory strategyFactory;
    private BoardFactory boardFactory;
    private User winner;

    public TicTacToeGame(List<Symbol> symbolList, List<User> users) {
        this.symbolList = symbolList;
        this.users = users;
        this.moves = new ArrayList<>();
        this.boardFactory = new BoardFactory();
        this.strategyFactory = new StrategyFactory();
        this.board = this.boardFactory.createBoard(users.size());
        generateSymbolUserMap();
        createTurns();
    }

    public void printGameCurrentStatus() {
        board.printBoard();
    }

    private void generateSymbolUserMap() {
        symbolUserMap = new HashMap<>();
        for (int i = 0; i < users.size(); i++) {
            symbolUserMap.put(symbolList.get(i).getSign(), users.get(i));
        }
    }

    private void createTurns() {
        turnQueue = new LinkedList<>();
        turnQueue.addAll(users);
    }

    public boolean takeMove(Move move) {
        if (!validateMove(move)) {
            return false;
        }
        this.moves.add(move);
        this.board.updateCell(move);
        User currUser = this.turnQueue.remove();
        this.turnQueue.add(currUser);
        return true;
    }


    public boolean checkAnyWinner() {
        boolean isThereAnyWinner = this.strategyFactory.getWinnerStrategy(this.board).isThereIsAnyWinner(board);
        if(isThereAnyWinner){
            this.winner = moves.getLast().getUser();
            return true;
        }
        return false;
    }

    private boolean validateMove(Move move) {
        User user = turnQueue.peek();
        if (move.getUser() != user) {
            System.out.println("Invalid Move");
            return false;
        }
        if (move.getXPos() > board.getSize() || move.getYPos() > board.getSize() || move.getYPos() <= 0 || move.getXPos() <= 0) {
            System.out.println("Invalid Move");
            return false;
        }
        if (board.isCellAlreadyOccupied(move.getXPos()-1, move.getYPos()-1)) {
            System.out.println("Invalid Move");
            return false;
        }
        return true;
    }

    public User getUser(String symbol) {
        if (symbolUserMap.containsKey(symbol)) {
            return symbolUserMap.get(symbol);
        }
        System.out.print("Invalid Move");
        return null;
    }

    public User getWinner(){
        return this.winner;
    }

    public int getTotalMoves(){
        return board.getSize()*board.getSize();
    }
}

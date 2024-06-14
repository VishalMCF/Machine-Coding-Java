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


public class TicTacToeGame {

    private List<Symbol> symbolList;
    private List<User> users;
    private Board board;
    private Map<String, User> symbolUserMap;
    private Queue<User> turnQueue;
    private List<Move> moves;

    private User winner;

    public TicTacToeGame(List<Symbol> symbolList, List<User> users) {
        this.symbolList = symbolList;
        this.users = users;
        this.moves = new ArrayList<>();
        this.board = new Board(users.size());
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
        moves.add(move);
        board.updateCell(move);
        return true;
    }


    public boolean checkAnyWinner() {
        int size = board.getSize();

        // validate vertical rows
        for (int j = 0; j < size; j++) {
            // scroll through one column
            Symbol firstSymbol = board.getCell(0, j);
            boolean isAllCrossed = true;
            for (int i = 1; i < size; i++) {
                Symbol currSymbol = board.getCell(i, j);
                if (currSymbol == null || currSymbol.getSign().equals(firstSymbol.getSign())) {
                    // ignore this column and move forward
                    isAllCrossed = false;
                    continue;
                }
            }
            if (isAllCrossed) {
                this.winner = moves.getLast().getUser();
                return true;
            }
        }

        // validate horizontal rows
        for (int i = 0; i < size; i++) {
            // scroll throw one row
            Symbol firstSymbol = board.getCell(i, 0);
            boolean isAllCrossed = true;
            for (int j = 1; j < size; j++) {
                Symbol currSymbol = board.getCell(i, j);
                if (currSymbol == null || currSymbol.getSign().equals(firstSymbol.getSign())) {
                    // ignore this column and move forward
                    isAllCrossed = false;
                    continue;
                }
            }
            if (isAllCrossed) {
                this.winner = moves.getLast().getUser();
                return true;
            }
        }

        // validate top left to bottom right diagonal
        Symbol firstSymbol = board.getCell(0, 0);
        boolean isAllCrossed = true;
        for (int i = 0; i < size; i++) {
            Symbol currSymbol = board.getCell(i, i);
            if (currSymbol == null || currSymbol.getSign().equals(firstSymbol.getSign())) {
                // ignore this column and move forward
                isAllCrossed = false;
                break;
            }
        }

        if (isAllCrossed) {
            this.winner = moves.getLast().getUser();
            return true;
        }

        // validate top right to bottom left diagonal
        firstSymbol = board.getCell(0, size - 1);
        isAllCrossed = true;
        for (int i = 1; i < size; i++) {
            Symbol currSymbol = board.getCell(i, size - 1 - i);
            if (currSymbol == null || currSymbol.getSign().equals(firstSymbol.getSign())) {
                // ignore this column and move forward
                isAllCrossed = false;
                break;
            }
        }

        if(isAllCrossed){
            this.winner = moves.getLast().getUser();
        }

        return isAllCrossed;
    }

    private boolean validateMove(Move move) {
        User user = turnQueue.peek();
        if (move.getUser() != user) {
            System.out.print("Invalid Move");
            return false;
        }
        if (move.getXPos() >= board.getSize() || move.getYPos() >= board.getSize() || move.getYPos() < 0 || move.getXPos() < 0) {
            System.out.print("Invalid Move");
            return false;
        }
        if (board.isCellAlreadyOccupied(move.getXPos(), move.getYPos())) {
            System.out.print("Invalid Move");
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
}

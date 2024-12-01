package com.gatomalvado.done.tictactoe.model;

public class Board {

    private Symbol[][] cells;
    private int size;

    public Board(int size) {
        this.size = size;
        this.cells = new Symbol[size][size];
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Symbol("_");
            }
            System.out.println();
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] != null) {
                    System.out.print(cells[i][j] + " ");
                } else {
                    System.out.print("_" + " ");
                }
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isCellAlreadyOccupied(int x, int y) {
        return !cells[x][y].getSign().equals("_");
    }

    public void updateCell(Move move){
        this.cells[move.getXPos()-1][move.getYPos()-1] = move.getUser().getSymbol();
    }

    public Symbol getCell(int x, int y){
        return this.cells[x][y];
    }
}

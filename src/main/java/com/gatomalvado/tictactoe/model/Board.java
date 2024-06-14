package com.gatomalvado.tictactoe.model;




public class Board {

    private Symbol[][] cells;
    private int size;

    public Board(int size) {
        this.size = size;
        if(size==2){
            cells = new Symbol[size+1][size+1];
        }else if(size > 2){
            cells = new Symbol[size+][]
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
        return cells[x][y] != null;
    }

    public void updateCell(Move move){
        this.cells[move.getXPos()][move.getYPos()] = move.getUser().getSymbol();
    }

    public Symbol getCell(int x, int y){
        return this.cells[x][y];
    }
}

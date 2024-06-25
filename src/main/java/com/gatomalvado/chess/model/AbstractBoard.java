package com.gatomalvado.chess.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public abstract class AbstractBoard {

    protected Piece[][] cells;

    protected AbstractBoard(int size) {
        this.cells = new Piece[size][size];
    }

    public void addPieceToTheBoard(Piece piece){
        this.cells[piece.getPosition().getXPos()][piece.getPosition().getYPos()] = piece;
    }

    public void removePieceFromBoard(Piece piece){
        this.cells[piece.getPosition().getXPos()][piece.getPosition().getYPos()] = null;
    }

    public void modifyPositionOfPiece(Piece piece, Position newPosition){
        removePieceFromBoard(piece);
        piece.setPosition(newPosition);
        addPieceToTheBoard(piece);
    }

    public void printBoardStatus(){
        for(int i=0; i<getSize(); i++) {
            for(int j=0; j<getSize(); j++){
                if(cells[i][j] != null){
                    System.out.print(cells[i][j].toString()+" ");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public abstract int getSize();

    public abstract Position getPosition(String input);

    public abstract Piece getPiece(Position pos);
}

package com.gatomalvado.done.tictactoe.service;

import com.gatomalvado.done.tictactoe.model.Board;

public class BoardFactory {
    public Board createBoard(int noOfUsers){
        switch(noOfUsers){
            case 2:
                return new Board(3);
            default:
                throw new UnsupportedOperationException("This is not supported yet");
        }
    }
}

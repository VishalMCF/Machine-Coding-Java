package com.gatomalvado.chess.service.impl;

import com.gatomalvado.chess.model.AbstractBoard;
import com.gatomalvado.chess.model.Board8;
import com.gatomalvado.chess.service.IBoardInitializer;
import com.gatomalvado.chess.service.IPieceMoveValidator;
import com.gatomalvado.chess.service.impl.board8.Board8Initializer;
import com.gatomalvado.chess.service.impl.board8.Board8PieceMoveValidator;

public class BoardFactory {

    private final IBoardInitializer board8Initializer;

    private final IPieceMoveValidator board8PieceMoveValidator;

    public BoardFactory() {
        this.board8Initializer = new Board8Initializer();
        this.board8PieceMoveValidator = new Board8PieceMoveValidator();
    }

    public Board8 generateBoard(int size) {
        switch (size) {
            case 8:
                return new Board8();
            default:
                throw new UnsupportedOperationException("Method {generateBoard} : THis is not supported yet");
        }
    }

    public void initializeBoard(AbstractBoard board) {
        switch (board.getSize()) {
            case 8:
                board8Initializer.initializeBoard(board);
                return;
            default:
                throw new UnsupportedOperationException("Method {initializeBoard} :THis is not supported yet");
        }
    }

    public IPieceMoveValidator getBoard8PieceMoveValidator(AbstractBoard board) {
        switch (board.getSize()) {
            case 8:
                return board8PieceMoveValidator;
            default:
                throw new UnsupportedOperationException("Method {initializeBoard} :THis is not supported yet");
        }
    }

}

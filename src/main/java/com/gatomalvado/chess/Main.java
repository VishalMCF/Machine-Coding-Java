package com.gatomalvado.chess;

import com.gatomalvado.chess.model.AbstractBoard;
import com.gatomalvado.chess.model.Board8;
import com.gatomalvado.chess.model.Player;
import com.gatomalvado.chess.model.enums.PlayerType;
import com.gatomalvado.chess.orchestrator.ChessOrchestrator;
import com.gatomalvado.chess.service.impl.BoardFactory;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Chess validator");
        AbstractBoard abstractBoard = new Board8();
        BoardFactory boardFactory = new BoardFactory();
        ChessOrchestrator chessOrchestrator = new ChessOrchestrator(abstractBoard, boardFactory);
        chessOrchestrator.addPlayer(new Player("P1", PlayerType.WHITE));
        chessOrchestrator.addPlayer(new Player("P2", PlayerType.BLACK));
        chessOrchestrator.initializeBoard();
        chessOrchestrator.printBoard();
    }
}

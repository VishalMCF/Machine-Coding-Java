package com.gatomalvado.done.chess;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Board8;
import com.gatomalvado.done.chess.model.Player;
import com.gatomalvado.done.chess.model.enums.PlayerType;
import com.gatomalvado.done.chess.orchestrator.ChessOrchestrator;
import com.gatomalvado.done.chess.service.impl.BoardFactory;

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

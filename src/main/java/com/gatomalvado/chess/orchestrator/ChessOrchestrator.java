package com.gatomalvado.chess.orchestrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gatomalvado.chess.model.AbstractBoard;
import com.gatomalvado.chess.model.Move;
import com.gatomalvado.chess.model.Piece;
import com.gatomalvado.chess.model.Player;
import com.gatomalvado.chess.model.Position;
import com.gatomalvado.chess.service.impl.BoardFactory;
import com.gatomalvado.chess.service.IPieceMoveValidator;

import lombok.Getter;

public class ChessOrchestrator {

    private final Map<String, Piece> pieceMap;

    private final List<Player> playerList;

    private final AbstractBoard chessBoard;

    private final BoardFactory boardFactory;

    @Getter
    private Player winner;

    public ChessOrchestrator(AbstractBoard chessBoard, BoardFactory boardFactory) {
        this.pieceMap = new HashMap<>();
        this.playerList = new ArrayList<>();
        this.chessBoard = chessBoard;
        this.boardFactory = boardFactory;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public void initializeBoard() {
        boardFactory.initializeBoard(chessBoard);
    }

    public void printBoard() {
        chessBoard.printBoardStatus();
    }

    public boolean movePiece(String startPos, String finalPos, Player player) {
        Position oldPosition = chessBoard.getPosition(finalPos);
        Position newPosition = chessBoard.getPosition(startPos);
        Piece currPiece      = chessBoard.getPiece(oldPosition);

        Move move = new Move(currPiece, player, newPosition);

        IPieceMoveValidator validator = boardFactory.getBoard8PieceMoveValidator(chessBoard);

        if(!validator.validate(move, chessBoard)){
            System.out.println("Provided input is not valid. Please try again later");
            return false;
        }

        Piece piece = chessBoard.getPiece(newPosition);
        if(piece != null){
            chessBoard.removePieceFromBoard(piece);
        }
        chessBoard.modifyPositionOfPiece(piece, newPosition);
        return true;
    }

}

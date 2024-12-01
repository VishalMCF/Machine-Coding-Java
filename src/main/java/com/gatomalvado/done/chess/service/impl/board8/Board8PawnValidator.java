package com.gatomalvado.done.chess.service.impl.board8;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Move;
import com.gatomalvado.done.chess.model.Piece;
import com.gatomalvado.done.chess.model.Position;
import com.gatomalvado.done.chess.model.enums.PlayerType;
import com.gatomalvado.done.chess.service.Board8PosValidator;

public class Board8PawnValidator implements Board8PosValidator {

    @Override
    public boolean validate(AbstractBoard board, Move move) {
        Position currPos = move.getPiece().getPosition();
        Position finalPos = move.getFinalPosition();
        Piece pieceOnFinalPos = board.getPiece(finalPos);
        PlayerType finalPosPlayerType = board.getPiece(finalPos)==null ? null : board.getPiece(finalPos).getPlayerType();
        PlayerType currPosPlayerType = move.getPiece().getPlayerType();
        if(currPosPlayerType.equals(finalPosPlayerType)){
            return false;
        }
        if(finalPosPlayerType == null){
            if(currPos.getYPos() != finalPos.getYPos()){
                return false;
            }
            if(currPosPlayerType.equals(PlayerType.WHITE)){
                // validate the direction as white should move forward
                if(currPos.getXPos() != finalPos.getXPos() + 1){
                    return false;
                }
            } else {
                // validate the direction as black should move downward
                if(currPos.getXPos() != finalPos.getXPos() - 1){
                    return false;
                }
            }
        } else {
            // we have already validated that the opposite player is of enemy type. Now we just have to validate the direction of attack
            // validate the direction, for black it can only be diagonal left or right and downwards
            // 1. validate dowards
            if(currPos.getXPos() != finalPos.getXPos() - 1) {
                return false;
            }
            // 2. left or right
            if(Math.abs(currPos.getYPos() - finalPos.getYPos()) != 1){
                return false;
            }
            // validate the direction, for white it can only be upwards
            if(currPos.getXPos() != finalPos.getXPos() + 1) {
                return false;
            }
        }
        return true;
    }
}

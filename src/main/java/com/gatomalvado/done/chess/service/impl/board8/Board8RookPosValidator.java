package com.gatomalvado.done.chess.service.impl.board8;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Move;
import com.gatomalvado.done.chess.model.Piece;
import com.gatomalvado.done.chess.model.Position;
import com.gatomalvado.done.chess.model.enums.PlayerType;
import com.gatomalvado.done.chess.service.Board8PosValidator;

public class Board8RookPosValidator implements Board8PosValidator {

    /**
     * 1. First check whether the same column or same row is present or not for column(x1 == x2) for row(y1 == y2)
     * 2. If the row is same then check whether to go left ot right. (y1 < y2)
     * 3. If you need to go right and if you encounter any non-empty cell before reaching the desired cell then return false
     * 4. If you need to go to left and if you encounter any non-empty cell before reaching the desired cell then return false
     * 5. If the column is same then check whether to go up or down (x1 > x2)
     * 6. same as 3rd and 4th validation
     * @param board
     * @param move
     * @return
     */
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
        if(currPos.getXPos() == finalPos.getXPos()){
            if(currPos.getYPos() < finalPos.getYPos()){

            } else {

            }
        }
        if(currPos.getYPos() == finalPos.getYPos()) {
            if(currPos.getXPos() > finalPos.getXPos()){

            } else {

            }
        }
        return true;
    }
}

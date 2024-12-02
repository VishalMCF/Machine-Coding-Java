package com.gatomalvado.done.chess.service.impl.board8;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Move;
import com.gatomalvado.done.chess.model.Piece;
import com.gatomalvado.done.chess.model.Position;
import com.gatomalvado.done.chess.model.enums.PlayerType;
import com.gatomalvado.done.chess.service.Board8PosValidator;

public class Board8BishopPosValidator implements Board8PosValidator {

    /**
     *       4 cases are possible but the most important thing is to check that the absolute value of the differences between
     *       x and y coordinates.
     *       Math.abs(x1-x2) != Math.abs(y1 - y2) then return false straight away
     *       if it satisfies, then determine which direction to move. There are 4 direction possibles which can be check using the
     *       below logic:
     *       a) if(x1 < x2 && y1 > y2) -> need to move to left  upward    diagonally
     *       b) if(x1 < x2 && y1 < y2) -> need to move to right upward    diagonally
     *       c) if(x1 > x2 && y1 < y2) -> need to move to right downward  diagonally
     *       d) if(x1 > x2 && y1 > y2) -> need to move to left  downward  diagonally
     *       if before reaching the final position you encounter any piece of any color then return false
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
        /**
         * TO DO:-
         */
        return true;
    }
}

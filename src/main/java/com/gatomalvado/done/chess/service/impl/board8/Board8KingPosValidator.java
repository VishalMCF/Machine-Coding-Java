package com.gatomalvado.done.chess.service.impl.board8;

import java.util.List;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Move;
import com.gatomalvado.done.chess.model.Position;
import com.gatomalvado.done.chess.service.Board8PosValidator;

public class Board8KingPosValidator implements Board8PosValidator {

    private static final List<List<Integer>> POSSIBLE_MOVES = List.of(List.of(1, 0), List.of(1, -1), List.of(1, 1), List.of(0, 1),
        List.of(0, -1), List.of(-1, 0), List.of(-1, -1), List.of(-1, 1));

    @Override
    public boolean validate(AbstractBoard board, Move move) {
        Position currPos = move.getPiece().getPosition();
        Position finalPos = move.getFinalPosition();
        if(board.getPiece(finalPos).getPlayerType().equals(move.getPlayer().getType())){
            return false;
        }
        for (int i = 0; i < POSSIBLE_MOVES.size(); i++) {
            Position temp = new Position(currPos.getXPos() + POSSIBLE_MOVES.get(i).getFirst(),
                currPos.getYPos() + POSSIBLE_MOVES.get(i).getLast());
            if (temp.isEquals(finalPos) && (board.getPiece(finalPos).getPieceType() != board.getPiece(currPos).getPieceType()
                || board.getPiece(finalPos) != null)) {
                return true;
            }
        }
        return false;
    }
}

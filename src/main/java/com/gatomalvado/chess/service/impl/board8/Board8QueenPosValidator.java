package com.gatomalvado.chess.service.impl.board8;

import java.util.List;

import com.gatomalvado.chess.model.AbstractBoard;
import com.gatomalvado.chess.model.Board8;
import com.gatomalvado.chess.model.Move;
import com.gatomalvado.chess.model.Position;
import com.gatomalvado.chess.service.Board8PosValidator;

public class Board8QueenPosValidator implements Board8PosValidator {

    private static final List<List<Integer>> POSSIBLE_MOVES = List.of(List.of(1, 0), List.of(1, -1), List.of(1, 1), List.of(0, 1),
        List.of(0, -1), List.of(-1, 0), List.of(-1, -1), List.of(-1, 1));


    @Override
    public boolean validate(AbstractBoard board, Move move) {
        Position currPos = move.getPiece().getPosition();
        Position finalPos = move.getFinalPosition();
        if(board.getPiece(finalPos).getPlayerType().equals(move.getPlayer().getType())){
            return false;
        }

        return false;
    }
}

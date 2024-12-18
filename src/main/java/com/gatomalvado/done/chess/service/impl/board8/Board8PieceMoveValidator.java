package com.gatomalvado.done.chess.service.impl.board8;

import java.util.HashMap;
import java.util.Map;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Move;
import com.gatomalvado.done.chess.model.enums.PieceType;
import com.gatomalvado.done.chess.service.Board8PosValidator;
import com.gatomalvado.done.chess.service.IPieceMoveValidator;

public class Board8PieceMoveValidator implements IPieceMoveValidator {

    private Map<PieceType, Board8PosValidator> validatorMap;

    public Board8PieceMoveValidator() {
        this.validatorMap = new HashMap<>();
        initMap();
    }

    @Override
    public boolean validate(Move move, AbstractBoard board) {
        return validatorMap.get(move.getPiece().getPieceType()).validate(board, move);
    }

    private void initMap(){
        validatorMap.put(PieceType.KING, new Board8KingPosValidator());
        validatorMap.put(PieceType.QUEEN, new Board8QueenPosValidator());
        validatorMap.put(PieceType.ROOK, new Board8RookPosValidator());
        validatorMap.put(PieceType.KNIGHT, new Board8KnightPosValidator());
        validatorMap.put(PieceType.BISHOP, new Board8BishopPosValidator());
        validatorMap.put(PieceType.PAWN, new Board8PawnValidator());
    }
}

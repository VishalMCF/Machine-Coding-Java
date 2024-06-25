package com.gatomalvado.chess.model.enums;

import java.util.List;

public enum PieceType {

    KING("king"),
    QUEEN("queen"),
    KNIGHT("knight"),
    ROOK("rook"),
    BISHOP("bishop"),
    PAWN("pawn");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }
}

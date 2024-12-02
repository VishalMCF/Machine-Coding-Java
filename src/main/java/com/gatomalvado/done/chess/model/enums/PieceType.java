package com.gatomalvado.done.chess.model.enums;

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

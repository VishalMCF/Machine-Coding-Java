package com.gatomalvado.chess.model;

import com.gatomalvado.chess.model.enums.PieceType;
import com.gatomalvado.chess.model.enums.PlayerType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Piece {

    private final String id;

    private final String name;

    private final PieceType pieceType;

    @Setter
    private final PlayerType playerType;

    @Setter
    private Position position;

    public Piece(String id, String name, PieceType pieceType, PlayerType playerType, Position position) {
        this.id = id;
        this.name = name;
        this.pieceType = pieceType;
        this.playerType = playerType;
        this.position = position;
    }

    @Override
    public String toString() {
        return name;
    }
}

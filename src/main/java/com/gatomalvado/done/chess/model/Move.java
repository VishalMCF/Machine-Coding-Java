package com.gatomalvado.done.chess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Move {
    private Piece piece;
    private Player player;
    private Position finalPosition;
}

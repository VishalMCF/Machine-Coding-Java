package com.gatomalvado.chess.model;

import com.gatomalvado.chess.model.enums.PlayerType;

import lombok.Getter;

@Getter
public class Player {

    private final String name;

    private final PlayerType type;

    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }
}

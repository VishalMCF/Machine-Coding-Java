package com.gatomalvado.done.snakesandladders.model.enums;

import lombok.Getter;

@Getter
public enum EntityType {
    PLAYER("player"),
    SNAKE("snake"),
    LADDER("ladder");

    private String name;

    EntityType(String name) {
        this.name = name;
    }
}

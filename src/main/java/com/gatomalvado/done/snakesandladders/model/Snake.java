package com.gatomalvado.done.snakesandladders.model;

import com.gatomalvado.done.snakesandladders.model.enums.EntityType;

import lombok.Getter;

@Getter
public class Snake extends Entity {

    public Snake(String id, int xPos, int yPos) {
        super(id, xPos, yPos, EntityType.SNAKE);
    }
}

package com.gatomalvado.done.snakesandladders.model;

import com.gatomalvado.done.snakesandladders.model.enums.EntityType;

import lombok.Getter;

@Getter
public class Ladder extends Entity {

    public Ladder(String id, int xPos, int yPos) {
        super(id, xPos, yPos, EntityType.LADDER);
    }
}

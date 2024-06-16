package com.gatomalvado.snakesandladders.model;

import com.gatomalvado.snakesandladders.model.enums.EntityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Ladder extends Entity {

    public Ladder(String id, int xPos, int yPos) {
        super(id, xPos, yPos, EntityType.LADDER);
    }
}

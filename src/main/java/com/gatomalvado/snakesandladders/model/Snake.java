package com.gatomalvado.snakesandladders.model;

import com.gatomalvado.snakesandladders.model.enums.EntityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Snake extends Entity {

    public Snake(String id, int xPos, int yPos) {
        super(id, xPos, yPos, EntityType.SNAKE);
    }
}

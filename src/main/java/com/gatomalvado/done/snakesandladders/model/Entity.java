package com.gatomalvado.done.snakesandladders.model;

import com.gatomalvado.done.snakesandladders.model.enums.EntityType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Entity {
    protected String id;
    protected int startPos;
    protected int endPos;
    protected EntityType type;
}

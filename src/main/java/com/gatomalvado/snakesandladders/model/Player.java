package com.gatomalvado.snakesandladders.model;

import com.gatomalvado.snakesandladders.model.enums.EntityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class Player {

    private String name;

    @Setter
    private int currPos;

    public Player(String name, int currPos) {
        this.name = name;
        this.currPos =  currPos;
    }

}

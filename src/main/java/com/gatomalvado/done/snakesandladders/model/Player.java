package com.gatomalvado.done.snakesandladders.model;

import lombok.Getter;
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

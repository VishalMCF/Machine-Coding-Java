package com.gatomalvado.done.chess.model;

import lombok.Getter;

@Getter
public class Position {
    private int xPos;
    private int yPos;

    public Position(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public boolean isEquals(Position position){
        return this.xPos==position.getXPos() && this.yPos==position.getYPos();
    }
}

package com.gatomalvado.done.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Move {
    private int xPos;
    private int yPos;
    private User user;

    @Override
    public String toString() {
        return user.getName()+" -> {"+xPos+", "+yPos+"}";
    }
}

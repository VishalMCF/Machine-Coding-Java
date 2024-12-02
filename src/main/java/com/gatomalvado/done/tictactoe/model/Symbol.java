package com.gatomalvado.done.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Symbol {
    private String sign;

    @Override
    public String toString() {
        return sign;
    }
}

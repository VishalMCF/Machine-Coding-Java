package com.gatomalvado.tictactoe.service.impl;

import com.gatomalvado.tictactoe.model.Board;
import com.gatomalvado.tictactoe.model.Symbol;
import com.gatomalvado.tictactoe.service.IWinnerDeciderStrategy;

public class TwoWinnerStrategy implements IWinnerDeciderStrategy {

    @Override
    public boolean isThereIsAnyWinner(Board board) {
        int size = board.getSize();

        // validate vertical rows
        for (int j = 0; j < size; j++) {
            // scroll through one column
            Symbol firstSymbol = board.getCell(0, j);
            boolean isAllCrossed = true;
            for (int i = 1; i < size; i++) {
                Symbol currSymbol = board.getCell(i, j);
                if (currSymbol == null || currSymbol.getSign().equals(firstSymbol.getSign())) {
                    // ignore this column and move forward
                    isAllCrossed = false;
                    continue;
                }
            }
            if (isAllCrossed) {
                return true;
            }
        }

        // validate horizontal rows
        for (int i = 0; i < size; i++) {
            // scroll throw one row
            Symbol firstSymbol = board.getCell(i, 0);
            boolean isAllCrossed = true;
            for (int j = 1; j < size; j++) {
                Symbol currSymbol = board.getCell(i, j);
                if (currSymbol == null || currSymbol.getSign().equals(firstSymbol.getSign())) {
                    // ignore this column and move forward
                    isAllCrossed = false;
                    continue;
                }
            }
            if (isAllCrossed) {
                return true;
            }
        }

        // validate top left to bottom right diagonal
        Symbol firstSymbol = board.getCell(0, 0);
        boolean isAllCrossed = true;
        for (int i = 0; i < size; i++) {
            Symbol currSymbol = board.getCell(i, i);
            if (currSymbol == null || currSymbol.getSign().equals(firstSymbol.getSign())) {
                // ignore this column and move forward
                isAllCrossed = false;
                break;
            }
        }

        if (isAllCrossed) {
            return true;
        }

        // validate top right to bottom left diagonal
        firstSymbol = board.getCell(0, size - 1);
        isAllCrossed = true;
        for (int i = 1; i < size; i++) {
            Symbol currSymbol = board.getCell(i, size - 1 - i);
            if (currSymbol == null || currSymbol.getSign().equals(firstSymbol.getSign())) {
                // ignore this column and move forward
                isAllCrossed = false;
                break;
            }
        }
        return isAllCrossed;
    }
}

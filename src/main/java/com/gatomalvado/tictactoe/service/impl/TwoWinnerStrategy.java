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
            boolean isAllCrossed = true;
            for (int i = 1; i < size; i++) {
                String currSymbolSign = board.getCell(i, j).getSign();
                String prevSymbolSign = board.getCell(0, j).getSign();
                if ("_".equals(prevSymbolSign) || !currSymbolSign.equals(prevSymbolSign)) {
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
                String prevSymbolSign = board.getCell(i, j-1).getSign();
                String currSymbolSign = board.getCell(i, j).getSign();
                if (!currSymbolSign.equals(prevSymbolSign) || currSymbolSign.equals("_")) {
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
        boolean isAllCrossed = true;
        for (int i = 1; i < size; i++) {
            String prevSymbolSign = board.getCell(i-1, i-1).getSign();
            String currSymbolSign = board.getCell(i, i).getSign();
            if (!prevSymbolSign.equals(currSymbolSign) || currSymbolSign.equals("_")) {
                // ignore this column and move forward
                isAllCrossed = false;
                break;
            }
        }

        if (isAllCrossed) {
            return true;
        }

        // validate top right to bottom left diagonal
        isAllCrossed = true;
        for (int i = 1; i < size; i++) {
            String currSymbolSign = board.getCell(i, size - 1 - i).getSign();
            String prevSymbolSign = board.getCell(i, size - 1 - (i-1)).getSign();
            if (!currSymbolSign.equals(prevSymbolSign) || currSymbolSign.equals("_")) {
                // ignore this column and move forward
                isAllCrossed = false;
                break;
            }
        }
        return isAllCrossed;
    }
}

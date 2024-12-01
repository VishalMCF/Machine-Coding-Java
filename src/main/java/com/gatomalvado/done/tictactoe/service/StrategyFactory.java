package com.gatomalvado.done.tictactoe.service;

import com.gatomalvado.done.tictactoe.model.Board;
import com.gatomalvado.done.tictactoe.service.impl.TwoWinnerStrategy;

public class StrategyFactory {

    private IWinnerDeciderStrategy twoWinnerStrategy;

    public StrategyFactory(){
        this.twoWinnerStrategy = new TwoWinnerStrategy();
    }

    public IWinnerDeciderStrategy getWinnerStrategy(Board board){
        switch(board.getSize()){
            case 3:
                return twoWinnerStrategy;
            default:
                throw new UnsupportedOperationException("This is not supported yet");
        }
    }
}

package com.gatomalvado.tictactoe.service;

import com.gatomalvado.tictactoe.model.Board;

public interface IWinnerDeciderStrategy {

    boolean isThereIsAnyWinner(Board board);
}

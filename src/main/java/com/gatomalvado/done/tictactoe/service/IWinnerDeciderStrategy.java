package com.gatomalvado.done.tictactoe.service;

import com.gatomalvado.done.tictactoe.model.Board;

public interface IWinnerDeciderStrategy {

    boolean isThereIsAnyWinner(Board board);
}

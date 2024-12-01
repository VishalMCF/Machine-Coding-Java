package com.gatomalvado.done.chess.service;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Move;

public interface IPieceMoveValidator {
    boolean validate(Move move, AbstractBoard board);
}

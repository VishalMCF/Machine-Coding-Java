package com.gatomalvado.done.chess.service;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Move;

public interface Board8PosValidator {

    boolean validate(AbstractBoard board, Move move);

}

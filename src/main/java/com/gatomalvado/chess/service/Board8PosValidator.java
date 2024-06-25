package com.gatomalvado.chess.service;

import com.gatomalvado.chess.model.AbstractBoard;
import com.gatomalvado.chess.model.Board8;
import com.gatomalvado.chess.model.Move;
import com.gatomalvado.chess.model.Piece;
import com.gatomalvado.chess.model.Position;
import com.gatomalvado.chess.model.Player;

public interface Board8PosValidator {

    boolean validate(AbstractBoard board, Move move);

}

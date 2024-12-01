package com.gatomalvado.done.chess.service;

import java.util.List;

import com.gatomalvado.done.chess.model.AbstractBoard;
import com.gatomalvado.done.chess.model.Piece;

public interface IBoardInitializer {
    public List<Piece> initializeBoard(AbstractBoard board);
}

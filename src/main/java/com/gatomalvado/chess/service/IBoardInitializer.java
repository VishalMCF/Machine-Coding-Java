package com.gatomalvado.chess.service;

import java.util.List;

import com.gatomalvado.chess.model.AbstractBoard;
import com.gatomalvado.chess.model.Piece;

public interface IBoardInitializer {
    public List<Piece> initializeBoard(AbstractBoard board);
}

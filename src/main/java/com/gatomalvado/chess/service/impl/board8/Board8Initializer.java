package com.gatomalvado.chess.service.impl.board8;

import static com.gatomalvado.chess.model.enums.PieceType.*;

import java.util.ArrayList;
import java.util.List;

import com.gatomalvado.chess.model.AbstractBoard;
import com.gatomalvado.chess.model.Piece;
import com.gatomalvado.chess.model.Position;
import com.gatomalvado.chess.model.enums.PlayerType;
import com.gatomalvado.chess.service.IBoardInitializer;

public class Board8Initializer implements IBoardInitializer {

    @Override
    public List<Piece> initializeBoard(AbstractBoard board) {
        List<Piece> allPieces = new ArrayList<>();

        // initialize white pawns
        for(int j=0; j< board.getSize(); j++){
            Position initialPos = new Position(1,j);
            Piece piece = new Piece("BP"+(j+1), "BP", PAWN, PlayerType.BLACK, initialPos);
            board.addPieceToTheBoard(piece);
            allPieces.add(piece);
        }

        // initialize black pawns
        for(int j=0; j< board.getSize(); j++){
            Position initialPos = new Position(6,j);
            Piece piece = new Piece("WP"+(j+1), "WP", PAWN, PlayerType.WHITE, initialPos);
            board.addPieceToTheBoard(piece);
            allPieces.add(piece);
        }

        // initialize white rooks
        Position positionWhiteRook1 = new Position(0,0);
        Position positionWhiteRook2 = new Position(0,7);
        Piece pieceWR1 = new Piece("BR1", "BR", ROOK, PlayerType.BLACK, positionWhiteRook1);
        Piece pieceWR2 = new Piece("BR2", "BR", ROOK, PlayerType.BLACK, positionWhiteRook2);
        board.addPieceToTheBoard(pieceWR1); board.addPieceToTheBoard(pieceWR2);
        allPieces.add(pieceWR1); allPieces.add(pieceWR2);

        // initialize white knights
        Position positionWhiteKnight1 = new Position(0,1);
        Position positionWhiteKnight2 = new Position(0,6);
        Piece pieceWK1 = new Piece("BN1", "BN", KNIGHT, PlayerType.BLACK, positionWhiteKnight1);
        Piece pieceWK2 = new Piece("BN2", "BN", KNIGHT, PlayerType.BLACK, positionWhiteKnight2);
        board.addPieceToTheBoard(pieceWK1); board.addPieceToTheBoard(pieceWK2);
        allPieces.add(pieceWK1); allPieces.add(pieceWK2);

        // initialize white bishops
        Position positionWhiteBishop1 = new Position(0,2);
        Position positionWhiteBishop2 = new Position(0,5);
        Piece pieceWB1 = new Piece("BB1", "BB", BISHOP, PlayerType.BLACK, positionWhiteBishop1);
        Piece pieceWB2 = new Piece("BB2", "BB", BISHOP, PlayerType.BLACK, positionWhiteBishop2);
        allPieces.add(pieceWB1); allPieces.add(pieceWB2);
        board.addPieceToTheBoard(pieceWB1); board.addPieceToTheBoard(pieceWB2);

        // initialize black queen
        Position positionWQueen = new Position(0, 3);
        Piece wQueen = new Piece("BQ", "BQ", QUEEN, PlayerType.BLACK, positionWQueen);
        allPieces.add(wQueen);
        board.addPieceToTheBoard(wQueen);

        // initialize black king
        Position positionWKing = new Position(0, 4);
        Piece wKing = new Piece("BK", "BK", KING, PlayerType.BLACK, positionWKing);
        allPieces.add(wKing);
        board.addPieceToTheBoard(wKing);

        // initialize black rooks
        Position positionBlackRook1 = new Position(7,0);
        Position positionBlackRook2 = new Position(7,7);
        Piece pieceBR1 = new Piece("WR1", "WR", ROOK, PlayerType.WHITE, positionBlackRook1);
        Piece pieceBR2 = new Piece("WR2", "WR", ROOK, PlayerType.WHITE, positionBlackRook2);
        allPieces.add(pieceBR1); allPieces.add(pieceBR2);
        board.addPieceToTheBoard(pieceBR1); board.addPieceToTheBoard(pieceBR2);

        // initialize black knights
        Position positionBlackKnight1 = new Position(7,1);
        Position positionBlackKnight2 = new Position(7,6);
        Piece pieceBK1 = new Piece("WN1", "WN", KNIGHT, PlayerType.WHITE, positionBlackKnight1);
        Piece pieceBK2 = new Piece("WN2", "WN", KNIGHT, PlayerType.WHITE, positionBlackKnight2);
        allPieces.add(pieceBK1); allPieces.add(pieceBK2);
        board.addPieceToTheBoard(pieceBK1); board.addPieceToTheBoard(pieceBK2);

        // initialize black bishops
        Position positionBlackBishop1 = new Position(7,2);
        Position positionBlackBishop2 = new Position(7,5);
        Piece pieceBB1 = new Piece("WB1", "WB", BISHOP, PlayerType.WHITE, positionBlackBishop1);
        Piece pieceBB2 = new Piece("WB2", "WB", BISHOP, PlayerType.WHITE, positionBlackBishop2);
        allPieces.add(pieceBB1); allPieces.add(pieceBB2);
        board.addPieceToTheBoard(pieceBB1); board.addPieceToTheBoard(pieceBB2);

        // initialize black king
        Position positionBKing = new Position(7,4);
        Piece bKing = new Piece("WK", "WK", KING, PlayerType.WHITE, positionBKing);
        allPieces.add(bKing);
        board.addPieceToTheBoard(bKing);

        // initialize black queen
        Position positionBQueen = new Position(7,3);
        Piece bQueen = new Piece("WQ", "WQ", QUEEN, PlayerType.WHITE, positionBQueen);
        allPieces.add(bQueen);
        board.addPieceToTheBoard(bQueen);

        return allPieces;

    }
}

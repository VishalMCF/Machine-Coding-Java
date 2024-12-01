package com.gatomalvado.done.chess.model;

import java.util.Map;

public class Board8 extends AbstractBoard {

    private static final int SIZE = 8;

    protected Map<Character, Integer> letterToColumnMap = Map.of('a',0, 'b',1,'c', 2,'d',3,'e',4,'f',5,'g',6,'h',7);
    protected Map<Character, Integer> numberToColumnMap = Map.of('8',0, '7',1,'6', 2,'5',3,'4',4,'3',5,'2',6,'1',7);

    public Board8() {
        super(SIZE);
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public Position getPosition(String input) {
        Character ch1 = input.charAt(0);
        Character ch2 = input.charAt(1);
        if(!numberToColumnMap.containsKey(ch1) || !letterToColumnMap.containsKey(ch2)){
            throw new UnsupportedOperationException("The input location format is not supported");
        }
        return new Position(numberToColumnMap.get(ch1), letterToColumnMap.get(ch2));
    }

    @Override
    public Piece getPiece(Position pos) {
        if(cells[pos.getXPos()][pos.getYPos()] != null){
            return cells[pos.getXPos()][pos.getXPos()];
        }
        return null;
    }
}

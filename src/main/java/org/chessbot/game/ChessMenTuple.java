package org.chessbot.game;

public abstract class ChessMenTuple extends ChessMen {

    public enum Side {
        LEFT,
        RIGHT;
    }

    public ChessMenTuple(Color color, Side side, char leftColumn, char rightColumn) {
        super(
            color,
            color == Color.WHITE ? ChessBoard.FIRST_ROW : ChessBoard.LAST_ROW,
            side == Side.LEFT ? leftColumn : rightColumn
        );
    }

}

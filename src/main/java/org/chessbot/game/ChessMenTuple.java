package org.chessbot.game;

import static org.chessbot.game.ChessBoard.FIRST_ROW;
import static org.chessbot.game.ChessBoard.LAST_ROW;
import static org.chessbot.game.ChessMen.Color.WHITE;

public abstract class ChessMenTuple extends ChessMen {

    public enum Side {
        LEFT,
        RIGHT;
    }

    public ChessMenTuple(Color color, Side side, char leftColumn, char rightColumn) {
        super(
            color,
            color == WHITE ? FIRST_ROW : LAST_ROW,
            side == Side.LEFT ? leftColumn : rightColumn
        );
    }

}

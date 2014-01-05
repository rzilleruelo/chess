package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;

public interface Rule {

    public static enum Mode {
        FUTURE_MOVE,
        CURRENT_MOVE;
    }

    public boolean apply(ChessBoard chessBoard, ChessMen chessMen, Mode mode);

    public boolean canMove(ChessBoard chessBoard, ChessMen chessMen, int row, char column);

}


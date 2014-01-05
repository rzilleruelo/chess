package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;

public class WhiteChessMenHasToMoveFirst implements Rule {

    @Override
    public boolean apply(ChessBoard chessBoard, ChessMen chessMen, Mode mode) {
        return mode == Mode.CURRENT_MOVE && !chessBoard.isInitialMoveIsMade();
    }

    @Override
    public boolean canMove(ChessBoard chessBoard, ChessMen chessMen, int row, char column) {
        return chessMen.getColor() == ChessMen.Color.WHITE;
    }

}

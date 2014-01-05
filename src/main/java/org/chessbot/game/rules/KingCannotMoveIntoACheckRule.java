package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.chessbot.game.King;

public class KingCannotMoveIntoACheckRule implements Rule {

    @Override
    public boolean apply(ChessBoard chessBoard, ChessMen chessMen, Mode mode) {
        return mode == Mode.CURRENT_MOVE;
    }

    @Override
    public boolean canMove(ChessBoard chessBoard, ChessMen chessMen, int row, char column) {
        return !chessBoard.anyCanMove(chessMen.getColor(), row, column);
    }

}


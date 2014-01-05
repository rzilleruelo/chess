package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;

public class KeepInsideBoardRule implements Rule {

    @Override
    public boolean apply(ChessBoard chessBoard, ChessMen chessMen, Mode mode) {
        return true;
    }

    @Override
    public boolean canMove(ChessBoard chessBoard, ChessMen chessMen, int row, char column) {
        return ChessBoard.FIRST_ROW <= row && row <= ChessBoard.LAST_ROW &&
               ChessBoard.FIRST_COLUMN <= column && column <= ChessBoard.LAST_COLUMN;
    }

}

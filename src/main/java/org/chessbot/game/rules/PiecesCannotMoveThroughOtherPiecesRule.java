package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.chessbot.game.Knight;

public class PiecesCannotMoveThroughOtherPiecesRule implements Rule {

    @Override
    public boolean apply(ChessBoard chessBoard, ChessMen chessMen, Mode mode) {
        return !chessMen.getClass().equals(Knight.class);
    }

    @Override
    public boolean canMove(ChessBoard chessBoard, ChessMen chessMen, int row, char column) {
        int dRow = row - chessMen.getRow();
        int dColumn = column - chessMen.getColumn();
        char sC = (char) Math.signum(dColumn);
        char c = (char) (chessMen.getColumn() + sC);
        if (dRow == 0) {
            for (; Math.abs(c - column) > 0; c += sC) {
                if (chessBoard.getChessMen(row, c) != null) return false;
            }
        } else {
            int sR = (int) Math.signum(dRow);
            int r = chessMen.getRow() + sR;
            for (; Math.abs(r - row) > 0; r += sR, c += sC) {
                if (chessBoard.getChessMen(r, c) != null) return false;
            }
        }
        return true;
    }

}


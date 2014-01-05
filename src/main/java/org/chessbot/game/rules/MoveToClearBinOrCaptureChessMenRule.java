package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;

public class MoveToClearBinOrCaptureChessMenRule implements Rule {

    @Override
    public boolean apply(ChessBoard chessBoard, ChessMen chessMen, Mode mode) {
        return true;
    }

    @Override
    public boolean canMove(ChessBoard chessBoard, ChessMen chessMen, int row, char column) {
        ChessMen newPositionChessMen = chessBoard.getChessMen(row, column);
        return newPositionChessMen == null || chessMen.getColor() != newPositionChessMen.getColor();
    }

}


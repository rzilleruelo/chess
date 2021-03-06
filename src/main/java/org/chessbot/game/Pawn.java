package org.chessbot.game;

import static org.chessbot.game.ChessBoard.FIRST_ROW;
import static org.chessbot.game.ChessBoard.LAST_ROW;
import static org.chessbot.game.ChessMen.Color.BLACK;
import static org.chessbot.game.ChessMen.Color.WHITE;

public class Pawn extends ChessMen {

    protected boolean firstMove;

    public Pawn(Color color, char column) {
        super(color, color == WHITE ? FIRST_ROW + 1 : LAST_ROW - 1, column);
        this.firstMove = true;
    }

    @Override
    public void setPosition(int row, char column) {
        super.setPosition(row, column);
        this.firstMove = false;
    }

    @Override
    protected boolean specificMoveRules(int row, char column) {
        int dRow = this.row - row;
        return (dRow < 0 && this.getColor() == WHITE) &&
               (dRow == -1 || (this.firstMove && dRow == -2)) ||
               (dRow > 0 && this.getColor() == BLACK) &&
               (dRow == 1 || (this.firstMove && dRow == 2));
    }

}

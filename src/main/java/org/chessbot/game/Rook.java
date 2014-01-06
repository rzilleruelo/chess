package org.chessbot.game;

public class Rook extends ChessMen {

    public enum Side {
        LEFT, RIGHT;
    }

    public Rook(Color color, Side side) {
        super(
            color,
            color == Color.WHITE ? ChessBoard.FIRST_ROW : ChessBoard.LAST_ROW,
            side == Side.LEFT ? ChessBoard.FIRST_COLUMN : ChessBoard.LAST_COLUMN
        );
    }

    @Override
    protected boolean specificMoveRules(int row, char column) {
        int dRow = this.row - row;
        int dColumn = this.column - column;
        return dRow == 0 || dColumn == 0;
    }
}

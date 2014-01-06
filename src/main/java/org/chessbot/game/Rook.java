package org.chessbot.game;

public class Rook extends ChessMenTuple {

    public Rook(Color color, Side side) {
        super(color, side, ChessBoard.FIRST_COLUMN, ChessBoard.LAST_COLUMN);
    }

    @Override
    protected boolean specificMoveRules(int row, char column) {
        int dRow = this.row - row;
        int dColumn = this.column - column;
        return dRow == 0 || dColumn == 0;
    }
}

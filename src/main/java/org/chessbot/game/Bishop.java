package org.chessbot.game;

public class Bishop extends ChessMenTuple {

    public Bishop(Color color, Side side) {
        super(color, side, 'c', 'f');
    }

    @Override
    protected boolean specificMoveRules(int row, char column) {
        int dRow = this.row - row;
        int dColumn = this.column - column;
        return Math.abs(dRow) == Math.abs(dColumn);
    }
}

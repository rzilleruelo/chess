package org.chessbot.game;

public class Knight extends ChessMenTuple {

    public Knight(Color color, Side side) {
        super(color, side, 'b', 'g');
    }

    @Override
    protected boolean specificMoveRules(int row, char column) {
        int dRow = Math.abs(this.row - row);
        int dColumn = Math.abs(this.column - column);
        return (dRow == 2 && dColumn == 1) || (dRow == 1 && dColumn == 2);
    }

}

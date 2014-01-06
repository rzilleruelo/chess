package org.chessbot.game;

public class King extends ChessMen {

    public King(Color color) {
        super(color, color == Color.WHITE ? ChessBoard.FIRST_ROW : ChessBoard.LAST_ROW, 'e');
    }

    @Override
    protected boolean specificMoveRules(int row, char column) {
        int dRow = this.row - row;
        int dColumn = this.column - column;
        return Math.abs(dRow) <= 1 && Math.abs(dColumn) <= 1;
    }

}

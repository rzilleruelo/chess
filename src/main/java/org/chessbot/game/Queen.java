package org.chessbot.game;

public class Queen extends ChessMen {

    public Queen(Color color) {
        super(color, color == Color.WHITE ? ChessBoard.FIRST_ROW : ChessBoard.LAST_ROW, 'd');
    }

    @Override
    public boolean canMove(int row, char column) {
        int dRow = this.row - row;
        int dColumn = this.column - column;
        return (dRow == 0 || dColumn == 0 || Math.abs(dRow) == Math.abs(dColumn));
    }

}

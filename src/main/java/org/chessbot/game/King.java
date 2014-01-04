package org.chessbot.game;

public class King extends ChessMen {

    protected King(Color color) {
        super(color, color == Color.WHITE ? 1 : 8, 'e');
    }

    @Override
    public boolean canMove(int row, char column) {
        int dRow = this.row - row;
        int dColumn = this.column - column;
        return (dRow == -1 || dRow == 1 || dColumn == -1 || dColumn == 1);
    }

}

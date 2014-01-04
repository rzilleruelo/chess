package org.chessbot.game;

public abstract class ChessMen {

    public static enum Color {
        WHITE,
        BLACK
    }

    protected Color color;
    protected int row;
    protected char column;

    protected ChessMen(Color color, int row, char column) {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    public Color getColor() {
        return this.color;
    }

    public int getRow() {
        return this.row;
    }

    public char getColumn() {
        return this.column;
    }

//    public void move(int row, char column) {
//        if (canMove(row, column) && shouldMove(row, column)) {
//            this.chessBoard.grid[this.row][this.column - 'a'] = null;
//            this.row = row;
//            this.column = column;
//            this.chessBoard.grid[this.row][this.column - 'a'] = this;
//        } else {
//            throw new IllegalArgumentException(String.format(
//                "%s cannot move to %s%d", this.getClass().getName(), column, row
//            ));
//        }
//    }

    public abstract boolean canMove(int row, char column);

}

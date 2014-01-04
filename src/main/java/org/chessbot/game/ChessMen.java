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

    public abstract boolean canMove(int row, char column);

}

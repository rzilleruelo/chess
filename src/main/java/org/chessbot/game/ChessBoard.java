package org.chessbot.game;

import org.chessbot.game.rules.ChessRulesSet;
import org.chessbot.game.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {

    public static final int BOARD_SIZE = 8;
    public static final int PLAYER_NUMBER_OF_PIECES = 16;
    public static final int FIRST_ROW = 1;
    public static final int LAST_ROW = 8;
    public static final char FIRST_COLUMN = 'a';
    public static final char LAST_COLUMN = 'h';

    protected boolean initialMoveIsMade;
    protected List<ChessMen> whiteChessMans;
    protected List<ChessMen> blackChessMans;
    protected ChessMen[][] grid;

    public ChessBoard() {
        this.initialMoveIsMade = false;
        this.whiteChessMans = new ArrayList<ChessMen>(ChessBoard.PLAYER_NUMBER_OF_PIECES);
        this.blackChessMans = new ArrayList<ChessMen>(ChessBoard.PLAYER_NUMBER_OF_PIECES);
        this.setup();
    }

    public boolean isInitialMoveIsMade() {
        return this.initialMoveIsMade;
    }

    public ChessMen getChessMen(int row, char column) {
        return this.grid[row - ChessBoard.FIRST_ROW][column - ChessBoard.FIRST_COLUMN];
    }

    public boolean anyCanMove(ChessMen.Color color, int row, char column) {
        if (color == ChessMen.Color.BLACK) {
            return this.anyCanMove(this.whiteChessMans, row, column);
        }
        return this.anyCanMove(this.blackChessMans, row, column);
    }

    public void move(int rowA, char columnA, int rowB, char columnB) {
        ChessMen chessMen = this.validateMove(rowA, columnA, rowB, columnB, Rule.Mode.CURRENT_MOVE);
        this.setChessMen(null, rowA, columnA);
        chessMen.setPosition(rowB, columnB);
        this.setChessMen(chessMen);
    }

    protected void setup() {
        this.initialMoveIsMade = false;
        this.whiteChessMans.clear();
        this.blackChessMans.clear();
        this.grid = new ChessMen[ChessBoard.BOARD_SIZE][ChessBoard.BOARD_SIZE];

        this.whiteChessMans.add(this.setChessMen(new King(ChessMen.Color.WHITE)));
        this.whiteChessMans.add(this.setChessMen(new Queen(ChessMen.Color.WHITE)));
        this.whiteChessMans.add(this.setChessMen(new Rook(ChessMen.Color.WHITE, Rook.Side.LEFT)));
        this.whiteChessMans.add(this.setChessMen(new Rook(ChessMen.Color.WHITE, Rook.Side.RIGHT)));

        this.blackChessMans.add(this.setChessMen(new King(ChessMen.Color.BLACK)));
        this.blackChessMans.add(this.setChessMen(new Queen(ChessMen.Color.BLACK)));
        this.blackChessMans.add(this.setChessMen(new Rook(ChessMen.Color.BLACK, Rook.Side.LEFT)));
        this.blackChessMans.add(this.setChessMen(new Rook(ChessMen.Color.BLACK, Rook.Side.RIGHT)));
    }

    protected ChessMen setChessMen(ChessMen chessMen) {
        return setChessMen(chessMen, chessMen.getRow(), chessMen.getColumn());
    }

    protected ChessMen setChessMen(ChessMen chessMen, int row, char column) {
        return this.grid[row - ChessBoard.FIRST_ROW][column - ChessBoard.FIRST_COLUMN] = chessMen;
    }

    protected ChessMen validateMove(
        int rowA,
        char columnA,
        int rowB,
        char columnB,
        Rule.Mode mode
    ) {
        ChessMen chessMen = this.getChessMen(rowA, columnA);
        if (chessMen == null) {
            if (mode == Rule.Mode.FUTURE_MOVE) return null;
            throw new IllegalArgumentException(String.format(
                "There is not chess men to move at %c%d",
                columnA,
                rowA
            ));
        }
        if (!chessMen.canMove(rowB, columnB)) {
            if (mode == Rule.Mode.FUTURE_MOVE) return null;
            throw new IllegalArgumentException(String.format(
                "%s cannot be moved to %c%d",
                chessMen.getClass().getName(),
                columnB,
                rowB
            ));
        }
        boolean validRules = ChessRulesSet.validateRules(this, chessMen, rowB, columnB, mode);
        return validRules ? chessMen : null;
    }

    protected boolean anyCanMove(List<ChessMen> chessMans, int row, char column) {
        for (ChessMen chessMen : chessMans) {
            if (validateMove(
                chessMen.getRow(),
                chessMen.getColumn(),
                row,
                column,
                Rule.Mode.FUTURE_MOVE
            ) != null) {
                return true;
            }
        }
        return false;
    }

}

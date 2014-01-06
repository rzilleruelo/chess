package org.chessbot.game;

import static org.chessbot.game.ChessMen.Color.BLACK;
import static org.chessbot.game.ChessMen.Color.WHITE;
import static org.chessbot.game.ChessMenTuple.Side.LEFT;
import static org.chessbot.game.ChessMenTuple.Side.RIGHT;
import org.chessbot.game.rules.ChessRulesSet;
import org.chessbot.game.rules.Rule;
import static org.chessbot.game.rules.Rule.Mode.CURRENT_MOVE;
import static org.chessbot.game.rules.Rule.Mode.FUTURE_MOVE;

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
    protected King whiteKing;
    protected King blackKing;
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

    public King getKing(ChessMen.Color color) {
        if (color == WHITE) return this.whiteKing;
        return this.blackKing;
    }

    public boolean anyCanMove(ChessMen.Color color, int row, char column) {
        if (color == BLACK) {
            return this.anyCanMove(this.whiteChessMans, row, column);
        }
        return this.anyCanMove(this.blackChessMans, row, column);
    }

    public void move(int rowA, char columnA, int rowB, char columnB) {
        ChessMen chessMen = this.validateMove(rowA, columnA, rowB, columnB, CURRENT_MOVE);
        this.setChessMen(null, rowA, columnA);
        chessMen.setPosition(rowB, columnB);
        this.setChessMen(chessMen);
    }

    protected void setup() {
        this.initialMoveIsMade = false;
        this.whiteChessMans.clear();
        this.blackChessMans.clear();
        this.grid = new ChessMen[ChessBoard.BOARD_SIZE][ChessBoard.BOARD_SIZE];

        this.whiteChessMans.add(this.setChessMen(this.whiteKing = new King(WHITE)));
        this.whiteChessMans.add(this.setChessMen(new Queen(WHITE)));
        this.whiteChessMans.add(this.setChessMen(new Rook(WHITE, LEFT)));
        this.whiteChessMans.add(this.setChessMen(new Rook(WHITE, RIGHT)));
        this.whiteChessMans.add(this.setChessMen(new Bishop(WHITE, LEFT)));
        this.whiteChessMans.add(this.setChessMen(new Bishop(WHITE, RIGHT)));
        this.whiteChessMans.add(this.setChessMen(new Knight(WHITE, LEFT)));
        this.whiteChessMans.add(this.setChessMen(new Knight(WHITE, RIGHT)));

        this.blackChessMans.add(this.setChessMen(this.blackKing = new King(BLACK)));
        this.blackChessMans.add(this.setChessMen(new Queen(BLACK)));
        this.blackChessMans.add(this.setChessMen(new Rook(BLACK, LEFT)));
        this.blackChessMans.add(this.setChessMen(new Rook(BLACK, RIGHT)));
        this.blackChessMans.add(this.setChessMen(new Bishop(BLACK, LEFT)));
        this.blackChessMans.add(this.setChessMen(new Bishop(BLACK, RIGHT)));
        this.blackChessMans.add(this.setChessMen(new Knight(BLACK, LEFT)));
        this.blackChessMans.add(this.setChessMen(new Knight(BLACK, RIGHT)));
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
            if (mode == FUTURE_MOVE) return null;
            throw new IllegalArgumentException(String.format(
                "There is not chess men to move at %c%d",
                columnA,
                rowA
            ));
        }
        if (!chessMen.canMove(rowB, columnB)) {
            if (mode == FUTURE_MOVE) return null;
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
                FUTURE_MOVE
            ) != null) {
                return true;
            }
        }
        return false;
    }

}

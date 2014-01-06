package org.chessbot.game;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.chessbot.game.Rook;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class RookTest {

    @Test
    public void leftWhiteRookCreationTest() {
        Rook rook = new Rook(ChessMen.Color.WHITE, Rook.Side.LEFT);
        assertEquals(ChessMen.Color.WHITE, rook.getColor());
        assertEquals(ChessBoard.FIRST_ROW, rook.getRow());
        assertEquals(ChessBoard.FIRST_COLUMN, rook.getColumn());
    }

    @Test
    public void rightWhiteRookCreationTest() {
        Rook rook = new Rook(ChessMen.Color.WHITE, Rook.Side.RIGHT);
        assertEquals(ChessMen.Color.WHITE, rook.getColor());
        assertEquals(ChessBoard.FIRST_ROW, rook.getRow());
        assertEquals(ChessBoard.LAST_COLUMN, rook.getColumn());
    }

    @Test
    public void leftBlackRookCreationTest() {
        Rook rook = new Rook(ChessMen.Color.BLACK, Rook.Side.LEFT);
        assertEquals(ChessMen.Color.BLACK, rook.getColor());
        assertEquals(ChessBoard.LAST_ROW, rook.getRow());
        assertEquals(ChessBoard.FIRST_COLUMN, rook.getColumn());
    }

    @Test
    public void rightBlackRookCreationTest() {
        Rook rook = new Rook(ChessMen.Color.BLACK, Rook.Side.RIGHT);
        assertEquals(ChessMen.Color.BLACK, rook.getColor());
        assertEquals(ChessBoard.LAST_ROW, rook.getRow());
        assertEquals(ChessBoard.LAST_COLUMN, rook.getColumn());
    }

    @Test
    public void shouldBeAbleToMoveTest() {
        Rook rook = new Rook(ChessMen.Color.WHITE, Rook.Side.LEFT);
        rook.setPosition(4, 'd');

        assertTrue(rook.canMove(5, 'd'));
        assertTrue(rook.canMove(4, 'f'));
        assertTrue(rook.canMove(1, 'd'));
        assertTrue(rook.canMove(4, 'b'));
    }

    @Test
    public void shouldNotBeAbleToMoveTest() {
        Rook rook = new Rook(ChessMen.Color.WHITE, Rook.Side.LEFT);
        rook.setPosition(4, 'd');

        assertFalse(rook.canMove(6, 'f'));
    }

}

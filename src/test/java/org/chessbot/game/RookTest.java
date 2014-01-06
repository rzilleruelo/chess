package org.chessbot.game;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import static org.chessbot.game.ChessMen.Color.BLACK;
import static org.chessbot.game.ChessMen.Color.WHITE;
import static org.chessbot.game.ChessMenTuple.Side.LEFT;
import static org.chessbot.game.ChessMenTuple.Side.RIGHT;
import static org.chessbot.game.ChessBoard.FIRST_COLUMN;
import static org.chessbot.game.ChessBoard.FIRST_ROW;
import static org.chessbot.game.ChessBoard.LAST_COLUMN;
import static org.chessbot.game.ChessBoard.LAST_ROW;

import org.junit.Test;

public class RookTest {

    @Test
    public void leftWhiteRookCreationTest() {
        Rook rook = new Rook(WHITE, LEFT);
        assertEquals(WHITE, rook.getColor());
        assertEquals(FIRST_ROW, rook.getRow());
        assertEquals(FIRST_COLUMN, rook.getColumn());
    }

    @Test
    public void rightWhiteRookCreationTest() {
        Rook rook = new Rook(WHITE, RIGHT);
        assertEquals(WHITE, rook.getColor());
        assertEquals(FIRST_ROW, rook.getRow());
        assertEquals(LAST_COLUMN, rook.getColumn());
    }

    @Test
    public void leftBlackRookCreationTest() {
        Rook rook = new Rook(BLACK, Rook.Side.LEFT);
        assertEquals(BLACK, rook.getColor());
        assertEquals(LAST_ROW, rook.getRow());
        assertEquals(FIRST_COLUMN, rook.getColumn());
    }

    @Test
    public void rightBlackRookCreationTest() {
        Rook rook = new Rook(BLACK, RIGHT);
        assertEquals(BLACK, rook.getColor());
        assertEquals(LAST_ROW, rook.getRow());
        assertEquals(LAST_COLUMN, rook.getColumn());
    }

    @Test
    public void shouldBeAbleToMoveTest() {
        Rook rook = new Rook(WHITE, LEFT);
        rook.setPosition(4, 'd');

        assertTrue(rook.canMove(5, 'd'));
        assertTrue(rook.canMove(4, 'f'));
        assertTrue(rook.canMove(1, 'd'));
        assertTrue(rook.canMove(4, 'b'));
    }

    @Test
    public void shouldNotBeAbleToMoveTest() {
        Rook rook = new Rook(WHITE, LEFT);
        rook.setPosition(4, 'd');

        assertFalse(rook.canMove(6, 'f'));
    }

}

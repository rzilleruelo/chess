package org.chessbot.game;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class QueenTest {

    @Test
    public void whiteQueenCreationTest() {
        Queen queen = new Queen(ChessMen.Color.WHITE);
        assertEquals(ChessMen.Color.WHITE, queen.getColor());
        assertEquals(ChessBoard.FIRST_ROW, queen.getRow());
        assertEquals('d', queen.getColumn());
    }

    @Test
    public void blackQueenCreationTest() {
        Queen queen = new Queen(ChessMen.Color.BLACK);
        assertEquals(ChessMen.Color.BLACK, queen.getColor());
        assertEquals(ChessBoard.LAST_ROW, queen.getRow());
        assertEquals('d', queen.getColumn());
    }

    @Test
    public void shouldBeAbleToMoveTest() {
        Queen queen = new Queen(ChessMen.Color.WHITE);
        queen.row = 4;
        queen.column = 'd';

        assertTrue(queen.canMove(5, 'd'));
        assertTrue(queen.canMove(6, 'f'));
        assertTrue(queen.canMove(4, 'h'));
        assertTrue(queen.canMove(3, 'e'));
        assertTrue(queen.canMove(2, 'd'));
        assertTrue(queen.canMove(2, 'b'));
        assertTrue(queen.canMove(4, 'b'));
        assertTrue(queen.canMove(7, 'a'));
    }

    @Test
    public void shouldNotBeAbleToMoveTest() {
        Queen queen = new Queen(ChessMen.Color.WHITE);
        queen.row = 4;
        queen.column = 'd';

        assertFalse(queen.canMove(6, 'g'));
    }

}

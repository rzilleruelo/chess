package org.chessbot.game;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import static org.chessbot.game.ChessMen.Color.BLACK;
import static org.chessbot.game.ChessMen.Color.WHITE;
import static org.chessbot.game.ChessBoard.FIRST_ROW;
import static org.chessbot.game.ChessBoard.LAST_ROW;

import org.junit.Test;

public class QueenTest {

    @Test
    public void whiteQueenCreationTest() {
        Queen queen = new Queen(WHITE);
        assertEquals(WHITE, queen.getColor());
        assertEquals(FIRST_ROW, queen.getRow());
        assertEquals('d', queen.getColumn());
    }

    @Test
    public void blackQueenCreationTest() {
        Queen queen = new Queen(BLACK);
        assertEquals(BLACK, queen.getColor());
        assertEquals(LAST_ROW, queen.getRow());
        assertEquals('d', queen.getColumn());
    }

    @Test
    public void shouldBeAbleToMoveTest() {
        Queen queen = new Queen(WHITE);
        queen.setPosition(4, 'd');

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
        Queen queen = new Queen(WHITE);
        queen.setPosition(4, 'd');

        assertFalse(queen.canMove(6, 'g'));
    }

}

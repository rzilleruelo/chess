package org.chessbot.game;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

import static org.chessbot.game.ChessMen.Color.BLACK;
import static org.chessbot.game.ChessMen.Color.WHITE;
import static org.chessbot.game.ChessBoard.FIRST_ROW;
import static org.chessbot.game.ChessBoard.LAST_ROW;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class KingTest {

    private final ChessBoard chessBoard = mock(ChessBoard.class);

    @Test
    public void whiteKingCreationTest() {
        King king = new King(WHITE);
        assertEquals(WHITE, king.getColor());
        assertEquals(FIRST_ROW, king.getRow());
        assertEquals('e', king.getColumn());
    }

    @Test
    public void blackKingCreationTest() {
        King king = new King(BLACK);
        assertEquals(BLACK, king.getColor());
        assertEquals(LAST_ROW, king.getRow());
        assertEquals('e', king.getColumn());
    }

    @Test
    public void shouldBeAbleToMoveTest() {
        King king = new King(WHITE);
        king.setPosition(4, 'd');

        assertTrue(king.canMove(5, 'd'));
        assertTrue(king.canMove(5, 'e'));
        assertTrue(king.canMove(4, 'e'));
        assertTrue(king.canMove(3, 'e'));
        assertTrue(king.canMove(3, 'd'));
        assertTrue(king.canMove(3, 'c'));
        assertTrue(king.canMove(4, 'c'));
        assertTrue(king.canMove(5, 'c'));
    }

    @Test
    public void shouldNotBeAbleToMoveTest() {
        King king = new King(WHITE);
        king.setPosition(4, 'd');

        assertFalse(king.canMove(4, 'd'));
        assertFalse(king.canMove(6, 'd'));
    }

}


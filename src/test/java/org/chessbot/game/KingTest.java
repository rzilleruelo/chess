package org.chessbot.game;


import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class KingTest {

    @Test
    public void testWhiteKingCreation() {
        King king = new King(ChessMen.Color.WHITE);
        assertEquals(ChessMen.Color.WHITE, king.getColor());
        assertEquals(ChessBoard.FIRST_ROW, king.getRow());
        assertEquals('e', king.getColumn());
    }

    @Test
    public void testBlackKingCreation() {
        King king = new King(ChessMen.Color.BLACK);
        assertEquals(ChessMen.Color.BLACK, king.getColor());
        assertEquals(ChessBoard.LAST_ROW, king.getRow());
        assertEquals('e', king.getColumn());
    }

    @Test
    public void testCanMove() {
        King king = new King(ChessMen.Color.WHITE);
        king.row = 4;
        king.column = 'd';

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
    public void testCannotMove() {
        King king = new King(ChessMen.Color.WHITE);
        king.row = 4;
        king.column = 'd';

        assertFalse(king.canMove(4, 'd'));
        assertFalse(king.canMove(6, 'd'));
    }

}


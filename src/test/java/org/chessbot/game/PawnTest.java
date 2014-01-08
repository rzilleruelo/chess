package org.chessbot.game;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.chessbot.game.ChessBoard.FIRST_COLUMN;
import static org.chessbot.game.ChessBoard.FIRST_ROW;
import static org.chessbot.game.ChessBoard.LAST_ROW;
import static org.chessbot.game.ChessMen.Color.BLACK;
import static org.chessbot.game.ChessMen.Color.WHITE;

import org.junit.Test;

public class PawnTest {

    @Test
    public void whitePawnCreationTest() {
        Pawn pawn = new Pawn(WHITE, FIRST_COLUMN);
        assertEquals(WHITE, pawn.getColor());
        assertEquals(FIRST_ROW + 1, pawn.getRow());
        assertEquals(FIRST_COLUMN, pawn.getColumn());
    }

    @Test
    public void blackPawnCreationTest() {
        Pawn pawn = new Pawn(BLACK, FIRST_COLUMN);
        assertEquals(BLACK, pawn.getColor());
        assertEquals(LAST_ROW - 1, pawn.getRow());
        assertEquals(FIRST_COLUMN, pawn.getColumn());
    }

    @Test
    public void onFirstMoveShouldBeAbleToMoveOneOrTwoRowsTest() {
        Pawn pawn = new Pawn(WHITE, FIRST_COLUMN);

        assertTrue(pawn.canMove(pawn.getRow() + 1, pawn.getColumn()));
        assertTrue(pawn.canMove(pawn.getRow() + 2, pawn.getColumn()));
    }

    @Test
    public void onNotFirstMoveShouldNotBeAbleToMoveTwoRowsTest() {
        Pawn pawn = new Pawn(WHITE, FIRST_COLUMN);
        pawn.firstMove = false;

        assertFalse(pawn.canMove(pawn.getRow() + 2, pawn.getColumn()));
    }

    @Test
    public void shouldNotBeAbleToMoveBackwardsTest() {
        Pawn pawn = new Pawn(WHITE, FIRST_COLUMN);
        pawn.firstMove = false;
        pawn.setPosition(4, 'd');

        assertFalse(pawn.canMove(pawn.getRow() - 1, pawn.getColumn()));
    }

    @Test
    public void shouldBeAbleToMoveLikeQueenWhenPromotedTest() {
        Pawn pawn = new Pawn(WHITE, FIRST_COLUMN);
        pawn.setPosition(4, 'd');

        boolean promoted = true;
        pawn.setPromotion(new Queen(WHITE));

        assertTrue(pawn.canMove(5, 'd', promoted));
        assertTrue(pawn.canMove(6, 'f', promoted));
        assertTrue(pawn.canMove(4, 'h', promoted));
        assertTrue(pawn.canMove(3, 'e', promoted));
        assertTrue(pawn.canMove(2, 'd', promoted));
        assertTrue(pawn.canMove(2, 'b', promoted));
        assertTrue(pawn.canMove(4, 'b', promoted));
        assertTrue(pawn.canMove(7, 'a', promoted));
    }

    @Test
    public void shouldBeAbleToNotMoveLikeQueenWhenPromotedTest() {
        Pawn pawn = new Pawn(WHITE, FIRST_COLUMN);
        pawn.setPosition(4, 'd');

        boolean promoted = true;
        pawn.setPromotion(new Queen(WHITE));

        assertFalse(pawn.canMove(6, 'g', promoted));
    }

}

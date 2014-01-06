package org.chessbot.game;

import static org.chessbot.game.ChessMen.Color.BLACK;
import static org.chessbot.game.ChessMen.Color.WHITE;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class ChessBoardTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private final ChessBoard chessBoard = new ChessBoard();

    @Test
    public void onFirstMoveIsInitialMoveTest() {
        assertFalse(chessBoard.isInitialMoveIsMade());
    }

    @Test
    public void afterFirstMoveIsInitialMoveTest() {
        chessBoard.initialMoveIsMade = true;

        assertTrue(chessBoard.isInitialMoveIsMade());
    }

    @Test
    public void getChessMenFromEmptyBin() {
        assertNull(chessBoard.getChessMen(4, 'e'));
    }

    @Test
    public void getChessMenFromNonEmptyBin() {
        assertThat(chessBoard.getChessMen(1, 'e'), instanceOf(ChessMen.class));
    }

    @Test
    public void anyCanMoveToAReachableBinTest() {
        assertTrue(chessBoard.anyCanMove(WHITE, 6, 'd'));
    }

    @Test
    public void anyCanMoveToANonReachableBinTest() {
        assertFalse(chessBoard.anyCanMove(WHITE, 4, 'c'));
    }

    @Test
    public void shouldMoveAValidMoveTest() {
        ChessMen expectedChessMen = chessBoard.getChessMen(2, 'e');
        chessBoard.move(2, 'e', 3, 'e');
        assertNull(chessBoard.getChessMen(2, 'e'));
        assertEquals(expectedChessMen, chessBoard.getChessMen(3, 'e'));
        assertEquals(3, expectedChessMen.getRow());
        assertEquals('e', expectedChessMen.getColumn());
    }

    @Test
    public void shouldNotMoveNonExistingChessMenTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("There is not chess men to move at d4");

        chessBoard.move(4, 'd', 2, 'e');
        assertNull(chessBoard.getChessMen(4, 'd'));
        assertNull(chessBoard.getChessMen(2, 'e'));
    }

    @Test
    public void shouldNotAllowAnInvalidMove() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("org.chessbot.game.King cannot be moved to e3");
        ChessMen expectedChessMen = chessBoard.getChessMen(1, 'e');

        chessBoard.move(1, 'e', 3, 'e');
        assertEquals(expectedChessMen, chessBoard.getChessMen(1, 'e'));
        assertNull(chessBoard.getChessMen(3, 'e'));
    }

    @Test
    public void shouldGetWhiteKingTest() {
        King king = chessBoard.getKing(WHITE);
        assertEquals(WHITE, king.getColor());
    }

    @Test
    public void shouldGetBlackKingTest() {
        King king = chessBoard.getKing(BLACK);
        assertEquals(BLACK, king.getColor());
    }

}

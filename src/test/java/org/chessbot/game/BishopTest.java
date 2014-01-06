package org.chessbot.game;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import static org.chessbot.game.ChessMen.Color.BLACK;
import static org.chessbot.game.ChessMen.Color.WHITE;
import static org.chessbot.game.ChessMenTuple.Side.LEFT;
import static org.chessbot.game.ChessMenTuple.Side.RIGHT;
import static org.chessbot.game.ChessBoard.FIRST_ROW;
import static org.chessbot.game.ChessBoard.LAST_ROW;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class BishopTest {

    private final ChessBoard chessBoard = mock(ChessBoard.class);

    @Test
    public void leftWhiteBishopCreationTest() {
        Bishop bishop = new Bishop(WHITE, LEFT);
        assertEquals(WHITE, bishop.getColor());
        assertEquals(FIRST_ROW, bishop.getRow());
        assertEquals('c', bishop.getColumn());
    }

    @Test
    public void rightWhiteBishopCreationTest() {
        Bishop bishop = new Bishop(WHITE, RIGHT);
        assertEquals(WHITE, bishop.getColor());
        assertEquals(FIRST_ROW, bishop.getRow());
        assertEquals('f', bishop.getColumn());
    }

    @Test
    public void leftBlackBishopCreationTest() {
        Bishop bishop = new Bishop(BLACK, LEFT);
        assertEquals(BLACK, bishop.getColor());
        assertEquals(LAST_ROW, bishop.getRow());
        assertEquals('c', bishop.getColumn());
    }

    @Test
    public void rightBlackRookCreationTest() {
        Bishop bishop = new Bishop(BLACK, RIGHT);
        assertEquals(BLACK, bishop.getColor());
        assertEquals(LAST_ROW, bishop.getRow());
        assertEquals('f', bishop.getColumn());
    }

    @Test
    public void shouldBeAbleToMoveTest() {
        Bishop bishop = new Bishop(WHITE, LEFT);
        bishop.setPosition(4, 'd');

        assertTrue(bishop.canMove(5, 'e'));
        assertTrue(bishop.canMove(2, 'f'));
        assertTrue(bishop.canMove(1, 'a'));
        assertTrue(bishop.canMove(6, 'b'));
    }

    @Test
    public void shouldNotBeAbleToMoveTest() {
        Bishop bishop = new Bishop(WHITE, LEFT);
        bishop.setPosition(4, 'd');

        assertFalse(bishop.canMove(6, 'd'));
    }

}

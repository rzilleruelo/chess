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

public class KnightTest {

    private final ChessBoard chessBoard = mock(ChessBoard.class);

    @Test
    public void leftWhiteKnightCreationTest() {
        Knight knight = new Knight(WHITE, LEFT);
        assertEquals(WHITE, knight.getColor());
        assertEquals(FIRST_ROW, knight.getRow());
        assertEquals('b', knight.getColumn());
    }

    @Test
    public void rightWhiteKnightCreationTest() {
        Knight knight = new Knight(WHITE, RIGHT);
        assertEquals(WHITE, knight.getColor());
        assertEquals(FIRST_ROW, knight.getRow());
        assertEquals('g', knight.getColumn());
    }

    @Test
    public void leftBlackKnightCreationTest() {
        Knight knight = new Knight(BLACK, LEFT);
        assertEquals(BLACK, knight.getColor());
        assertEquals(LAST_ROW, knight.getRow());
        assertEquals('b', knight.getColumn());
    }

    @Test
    public void rightBlackKnightCreationTest() {
        Knight knight = new Knight(BLACK, RIGHT);
        assertEquals(BLACK, knight.getColor());
        assertEquals(LAST_ROW, knight.getRow());
        assertEquals('g', knight.getColumn());
    }

    @Test
    public void shouldBeAbleToMoveTest() {
        Knight knight = new Knight(WHITE, LEFT);
        knight.setPosition(4, 'd');

        assertTrue(knight.canMove(6, 'e'));
        assertTrue(knight.canMove(5, 'f'));
        assertTrue(knight.canMove(3, 'f'));
        assertTrue(knight.canMove(2, 'e'));
        assertTrue(knight.canMove(2, 'c'));
        assertTrue(knight.canMove(3, 'b'));
        assertTrue(knight.canMove(5, 'b'));
        assertTrue(knight.canMove(6, 'c'));
    }

    @Test
    public void shouldNotBeAbleToMoveTest() {
        Knight knight = new Knight(WHITE, LEFT);
        knight.setPosition(4, 'd');

        assertFalse(knight.canMove(6, 'd'));
    }

}

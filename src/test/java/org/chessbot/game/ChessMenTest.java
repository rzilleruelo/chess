package org.chessbot.game;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ChessMenTest {

    private final ChessMen.Color expectedColor = ChessMen.Color.WHITE;
    private final int expectedRow = ChessBoard.FIRST_ROW;
    private final char expectedColumn = ChessBoard.FIRST_COLUMN;
    private final ChessMen chessMen = mock(ChessMen.class, Mockito.CALLS_REAL_METHODS);
    {
        chessMen.color = expectedColor;
        chessMen.row = expectedRow;
        chessMen.column = expectedColumn;
    }

    @Test
    public void getColorTest() {
        assertEquals(expectedColor, chessMen.getColor());
    }

    @Test
    public void getRowTest() {
        assertEquals(expectedRow, chessMen.getRow());
    }

    @Test
    public void getColumnTest() {
        assertEquals(expectedColumn, chessMen.getColumn());
    }

    @Test
    public void setPositionTest() {
        chessMen.setPosition(ChessBoard.LAST_ROW, ChessBoard.LAST_COLUMN);
        assertEquals(ChessBoard.LAST_ROW, chessMen.getRow());
        assertEquals(ChessBoard.LAST_COLUMN, chessMen.getColumn());
    }

    @Test
    public void shouldBeAbleToMoveToADifferentBinTest() {
        doReturn(true).when(chessMen).specificMoveRules(expectedRow + 1, (char) (expectedColumn + 1));
        assertTrue(chessMen.canMove(expectedRow + 1, (char) (expectedColumn + 1)));
    }

    @Test
    public void shouldNotBeAbleToMoveToSameBinTest() {
        doReturn(true).when(chessMen).specificMoveRules(expectedRow, expectedColumn);
        assertFalse(chessMen.canMove(expectedRow, expectedColumn));
    }

    @Test
    public void shouldNotBeAbleToMoveWhenSpecificMoveRulesIsFalseTest() {
        doReturn(false).when(chessMen).specificMoveRules(expectedRow + 1, (char) (expectedColumn + 1));
        assertFalse(chessMen.canMove(expectedRow + 1, (char) (expectedColumn + 1)));
    }

}

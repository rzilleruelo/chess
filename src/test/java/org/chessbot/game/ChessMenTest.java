package org.chessbot.game;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class ChessMenTest {

    private final ChessMen.Color expectedColor = ChessMen.Color.WHITE;
    private final int expectedRow = ChessBoard.FIRST_ROW;
    private final int expectedColumn = ChessBoard.FIRST_COLUMN;
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

}

package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WhiteChessMenHasToMoveFirstTest {

    private final WhiteChessMenHasToMoveFirst rule = new WhiteChessMenHasToMoveFirst();
    private final ChessBoard chessBoard = mock(ChessBoard.class);
    private final ChessMen chessMen = mock(ChessMen.class);

    @Test
    public void shouldApplyOnFirstMoveInCurrentMoveModeTest() {
        when(chessBoard.isInitialMoveIsMade()).thenReturn(false);

        assertTrue(rule.apply(chessBoard, chessMen, Rule.Mode.CURRENT_MOVE));
    }

    @Test
    public void shouldNotApplyOnFirstMoveInFutureMoveModeTest() {
        when(chessBoard.isInitialMoveIsMade()).thenReturn(false);

        assertFalse(rule.apply(chessBoard, chessMen, Rule.Mode.FUTURE_MOVE));
    }

    @Test
    public void shouldNotApplyAfterFirstMoveTest() {
        when(chessBoard.isInitialMoveIsMade()).thenReturn(true);

        assertFalse(rule.apply(chessBoard, chessMen, Rule.Mode.CURRENT_MOVE));
    }

    @Test
    public void aWhiteChessMenShouldBeAbleToMoveOnFirstMoveTest() {
        when(chessBoard.isInitialMoveIsMade()).thenReturn(false);
        when(chessMen.getColor()).thenReturn(ChessMen.Color.WHITE);

        assertTrue(rule.canMove(chessBoard, chessMen, ChessBoard.FIRST_ROW, ChessBoard.FIRST_COLUMN));
    }

    @Test
    public void aBlackChessMenShouldNotBeAbleToMoveOnFirstMoveTest() {
        when(chessBoard.isInitialMoveIsMade()).thenReturn(false);
        when(chessMen.getColor()).thenReturn(ChessMen.Color.BLACK);

        assertFalse(rule.canMove(chessBoard, chessMen, ChessBoard.FIRST_ROW, ChessBoard.FIRST_COLUMN));
    }

}

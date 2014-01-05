package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MoveToClearBinOrCaptureChessMenRuleTest {

    private final MoveToClearBinOrCaptureChessMenRule rule = new MoveToClearBinOrCaptureChessMenRule();
    private final ChessBoard chessBoard = mock(ChessBoard.class);
    private final ChessMen chessMen = mock(ChessMen.class);

    @Test
    public void shouldAlwaysApplyTest() {
        assertTrue(rule.apply(chessBoard, chessMen, Rule.Mode.CURRENT_MOVE));
    }

    @Test
    public void shouldBeAbleToMoveToEmptyBinTest() {
        when(chessBoard.getChessMen(0, ChessBoard.FIRST_COLUMN)).thenReturn(null);

        assertTrue(rule.canMove(chessBoard, chessMen, 0, ChessBoard.FIRST_COLUMN));
    }

    @Test
    public void aChessMenShouldBeAbleToMoveToBinTakenByOppositeColorChessMenTest() {
        ChessMen blackChessMen = mock(ChessMen.class);
        when(blackChessMen.getColor()).thenReturn(ChessMen.Color.BLACK);
        when(chessMen.getColor()).thenReturn(ChessMen.Color.WHITE);
        when(chessBoard.getChessMen(0, ChessBoard.FIRST_COLUMN)).thenReturn(blackChessMen);

        assertTrue(rule.canMove(chessBoard, chessMen, 0, ChessBoard.FIRST_COLUMN));
    }

    @Test
    public void aChessMenShouldNotBeAbleToMoveToBinTakenBySameColorChessMenTest() {
        ChessMen whiteChessMen = mock(ChessMen.class);
        when(whiteChessMen.getColor()).thenReturn(ChessMen.Color.WHITE);
        when(chessMen.getColor()).thenReturn(ChessMen.Color.WHITE);
        when(chessBoard.getChessMen(0, ChessBoard.FIRST_COLUMN)).thenReturn(whiteChessMen);

        assertFalse(rule.canMove(chessBoard, chessMen, 0, ChessBoard.FIRST_COLUMN));
    }

}

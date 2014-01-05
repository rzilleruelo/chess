package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PiecesCannotMoveThroughOtherPiecesRuleTest {

    private final PiecesCannotMoveThroughOtherPiecesRule rule = new PiecesCannotMoveThroughOtherPiecesRule();
    private final ChessBoard chessBoard = mock(ChessBoard.class);
    private final ChessMen chessMen = mock(ChessMen.class);

    @Test
    public void shouldAlwaysApplyTest() {
        assertTrue(rule.apply(chessBoard, chessMen, Rule.Mode.CURRENT_MOVE));
    }

    @Test
    public void shouldBeAbleToMoveWhenThereIsNoChessMansOnPathTest() {
        when(chessMen.getRow()).thenReturn(4);
        when(chessMen.getColumn()).thenReturn('d');
        when(chessBoard.getChessMen(5, 'd')).thenReturn(null);
        when(chessBoard.getChessMen(6, 'd')).thenReturn(null);
        when(chessBoard.getChessMen(7, 'd')).thenReturn(null);

        assertTrue(rule.canMove(chessBoard, chessMen, 8, 'd'));
    }

    @Test
    public void shouldNotBeAbleToMoveWhenThereIsChessMansOnPathTest() {
        when(chessMen.getRow()).thenReturn(4);
        when(chessMen.getColumn()).thenReturn('d');
        when(chessBoard.getChessMen(5, 'd')).thenReturn(null);
        when(chessBoard.getChessMen(6, 'd')).thenReturn(chessMen);
        when(chessBoard.getChessMen(7, 'd')).thenReturn(null);

        assertFalse(rule.canMove(chessBoard, chessMen, 8, 'd'));
    }

}

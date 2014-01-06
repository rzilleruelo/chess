package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import static org.chessbot.game.ChessMen.Color.WHITE;
import static org.chessbot.game.ChessMenTuple.Side.LEFT;
import static org.chessbot.game.rules.Rule.Mode.CURRENT_MOVE;

import org.chessbot.game.Knight;
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
        assertTrue(rule.apply(chessBoard, chessMen, CURRENT_MOVE));
    }

    @Test
    public void shouldNotApplyToKnight() {
        Knight knight = new Knight(WHITE, LEFT);
        assertFalse(rule.apply(chessBoard, knight, CURRENT_MOVE));
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

package org.chessbot.game.rules;

import static org.chessbot.game.ChessMen.Color.WHITE;
import static org.chessbot.game.rules.Rule.Mode.CURRENT_MOVE;
import static org.chessbot.game.rules.Rule.Mode.FUTURE_MOVE;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KingCannotMoveIntoACheckRuleTest {

    private final KingCannotMoveIntoACheckRule rule = new KingCannotMoveIntoACheckRule();
    private final ChessBoard chessBoard = mock(ChessBoard.class);
    private final ChessMen chessMen = mock(ChessMen.class);
    {
        when(chessMen.getColor()).thenReturn(WHITE);
    }

    @Test
    public void shouldApplyOnCurrentMoveTest() {
        assertTrue(rule.apply(chessBoard, chessMen, CURRENT_MOVE));
    }

    @Test
    public void shouldNotApplyOnFutureMoveTest() {
        assertFalse(rule.apply(chessBoard, chessMen, FUTURE_MOVE));
    }

    @Test
    public void shouldBeAbleToMoveToPositionUnreachableByAnyOppositeColorChessMenTest() {
        when(chessBoard.anyCanMove(chessMen.getColor(), 4, 'd')).thenReturn(false);

        assertTrue(rule.canMove(chessBoard, chessMen, 4, 'd'));
    }

    @Test
    public void shouldNotBeAbleToMoveToPositionReachableByAnyOppositeColorChessMenTest() {
        when(chessBoard.anyCanMove(chessMen.getColor(), 4, 'd')).thenReturn(true);

        assertFalse(rule.canMove(chessBoard, chessMen, 4, 'd'));
    }

}

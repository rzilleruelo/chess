package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class KeepInsideBoardRuleTest {

    private final KeepInsideBoardRule rule = new KeepInsideBoardRule();
    private final ChessBoard chessBoard = mock(ChessBoard.class);
    private final ChessMen chessMen = mock(ChessMen.class);

    @Test
    public void shouldAlwaysApplyTest() {
        assertTrue(rule.apply(chessBoard, chessMen, Rule.Mode.CURRENT_MOVE));
    }

    @Test
    public void shouldBeAbleToMoveWithinBoundsTest() {
        assertTrue(rule.canMove(chessBoard, chessMen, 4, 'd'));
    }

    @Test
    public void shouldNotBeAbleToMoveBeyondUpperBoundRowTest() {
        assertFalse(rule.canMove(chessBoard, chessMen, ChessBoard.LAST_ROW + 1, 'd'));
    }

    @Test
    public void shouldNotBeAbleToMoveBeyondLowerBoundRowTest() {
        assertFalse(rule.canMove(chessBoard, chessMen, ChessBoard.FIRST_ROW - 1, 'd'));
    }

    @Test
    public void shouldNotBeAbleToMoveBeyondUpperBoundColumnTest() {
        assertFalse(rule.canMove(chessBoard, chessMen, 4, (char) (ChessBoard.LAST_COLUMN + 1)));
    }

    @Test
    public void shouldNotBeAbleToMoveBeyondLowerBoundColumnTest() {
        assertFalse(rule.canMove(chessBoard, chessMen, 4, (char) (ChessBoard.FIRST_COLUMN - 1)));
    }

}

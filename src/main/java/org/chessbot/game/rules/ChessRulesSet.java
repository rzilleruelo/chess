package org.chessbot.game.rules;

import org.chessbot.game.ChessBoard;
import org.chessbot.game.ChessMen;
import org.chessbot.game.King;
import org.chessbot.game.Pawn;

import java.util.HashMap;
import java.util.Map;

public class ChessRulesSet {

    protected static final Rule[] generalRules = {
        new KeepInsideBoardRule(),
        new WhiteChessMenHasToMoveFirst(),
        new MoveToClearBinOrCaptureChessMenRule(),
        new PiecesCannotMoveThroughOtherPiecesRule(),
//        new KingHasToMoveWhenIsOnCheckRule()
    };

    protected static final Map<Class, Rule[]> chessMenRules = new HashMap<Class, Rule[]>() {{
        put(King.class, new Rule[] {
            new KingCannotMoveIntoACheckRule()
        });
//        put(Pawn.class, new Rule[] {
//            new PawnRestrictionsRule()
//        });
    }};

    public static boolean validateRules(
        ChessBoard chessBoard,
        ChessMen chessMen,
        int row,
        char column,
        Rule.Mode mode
    ) {
        boolean validRules = ChessRulesSet.validateRules(
            ChessRulesSet.generalRules,
            chessBoard,
            chessMen,
            row,
            column,
            mode
        );
        validRules = validRules && ChessRulesSet.validateRules(
            ChessRulesSet.chessMenRules.get(chessMen.getClass()),
            chessBoard,
            chessMen,
            row,
            column,
            mode
        );
        return validRules;
    }

    protected static boolean validateRules(
            Rule[] rules,
            ChessBoard chessBoard,
            ChessMen chessMen,
            int row,
            char column,
            Rule.Mode mode
    ) {
        if (rules == null) return true;
        boolean notValid;
        for (Rule rule : rules) {
            notValid = rule.apply(chessBoard, chessMen, mode);
            notValid = notValid && !rule.canMove(chessBoard, chessMen, row, column);
            if (notValid) {
                if (mode == Rule.Mode.FUTURE_MOVE) return false;
                throw new IllegalArgumentException(String.format(
                    "%s cannot be moved to %c%d because %s",
                    chessMen.getClass().getName(),
                    column,
                    row,
                    rule.getClass().getName()
                ));
            }
        }
        return true;
    }

}

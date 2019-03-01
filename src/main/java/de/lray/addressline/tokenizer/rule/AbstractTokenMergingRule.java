package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.Token;
import de.lray.addressline.tokenizer.token.TokenFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Combines functionality that all existing optimization rules have in common. An example
 * would be traversing over tokens.
 */
abstract class AbstractTokenMergingRule implements OptimizationRule {

    @Override
    public Token[] optimize(Token[] srcToken) {
        // TODO the current approach is resource intensive and should be replaced by an array copy-range solution instead
        List<Token> targetToken = new ArrayList<>();
        for (int i = 0; i < srcToken.length; i++) {
            Token aToken = srcToken[i];
            if ( mergeWithLastToken(srcToken, i, targetToken) == false  ) {
                targetToken.add(aToken);
            }
        }
        return targetToken.toArray(new Token[targetToken.size()]);
    }

    private boolean mergeWithLastToken(Token[] srcToken, int i, List<Token> targetToken) {
        Token aToken = srcToken[i];
        // TODO terrible hack to make the unit test pass - don't forget to refactor
        if (canLastTwoTokensBeMerged(srcToken, i, targetToken)) {
            targetToken.set(
                    targetToken.size() - 1,
                    TokenFactory.asMultiTypeToken(targetToken.get(targetToken.size() - 1).getValue() + " " + aToken.getValue()
                    )
            );
            return true;
        }
        return false;
    }

    abstract boolean canLastTwoTokensBeMerged(Token[] srcToken, int i, List<Token> targetToken);
}

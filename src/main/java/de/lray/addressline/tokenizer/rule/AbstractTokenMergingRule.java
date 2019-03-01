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
        List<Token> targetTokens = new ArrayList<>();
        for (int i = 0; i < srcToken.length; i++) {
            Token aToken = srcToken[i];
            if (i == 0 || !mergeWithLastToken(aToken, targetTokens)) {
                targetTokens.add(aToken);
            }
        }
        return targetTokens.toArray(new Token[targetTokens.size()]);
    }

    private boolean mergeWithLastToken(Token currentToken, List<Token> targetTokens) {
        final int lastIndex = targetTokens.size() - 1;
        final Token lastToken = targetTokens.isEmpty() ? null : targetTokens.get(lastIndex);
        if (lastToken != null && canLastTwoTokensBeMerged(lastToken, currentToken)) {
            final String newTokenValue = lastToken.getValue()
                    + " "
                    + currentToken.getValue();
            targetTokens.set(
                    lastIndex,
                    TokenFactory.asMultiTypeToken(newTokenValue)
            );
            return true;
        }
        return false;
    }

    abstract boolean canLastTwoTokensBeMerged(Token lastToken, Token currentToken);
}

package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.Token;
import de.lray.addressline.tokenizer.token.TokenFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Combines functionality that all existing optimization rules have in common. An example
 * would be traversing over tokens.
 */
abstract class AbstractTokenMergingRule implements OptimizationRule {

    @Override
    public Token[] optimize(Token[] srcToken) {
        Token[] targetTokens = new Token[srcToken.length];
        int count = -1;
        for (Token aToken : srcToken) {
            if (!mergeWithLastToken(aToken, targetTokens, count)) {
                targetTokens[++count] = aToken;
            }
        }
        return Arrays.copyOf(targetTokens, count + 1);
    }

    private boolean mergeWithLastToken(Token currentToken, Token[] targetTokens, int lastIndex) {
        if (lastIndex < 0) { return false; }
        final Token lastToken = targetTokens[lastIndex];
        if (canLastTwoTokensBeMerged(lastToken, currentToken)) {
            final String newTokenValue = lastToken.getValue()
                    + " "
                    + currentToken.getValue();
            targetTokens[lastIndex] = TokenFactory.asMultiTypeToken(newTokenValue);
            return true;
        }
        return false;
    }

    abstract boolean canLastTwoTokensBeMerged(Token lastToken, Token currentToken);
}

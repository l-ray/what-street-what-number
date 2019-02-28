package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.Token;
import de.lray.addressline.tokenizer.token.TokenFactory;

import java.util.*;

/**
 * Makes a very short string stick to a number
 *
 * Identify if a number has a short string as next element, and if so assumes it is a house number appendix. An example
 * would be "25" + "b" being connected to "25b".
 */
public class HouseNumberKeywordRule implements OptimizationRule {

    private final Set<String> numberPrefixes;

    public HouseNumberKeywordRule(String[] keywords) {
        numberPrefixes = new HashSet<>(Arrays.asList(keywords));
    }

    @Override
    public Token[] optimize(Token[] srcToken) {
        List<Token> targetToken = new ArrayList<>();
        for (int i = 0; i < srcToken.length; i++) {
            Token aToken = srcToken[i];
            // TODO terrible hack to make the unit test pass - don't forget to refactor
            if ( possiblyHandlePrefixLetter(srcToken, i, targetToken) == null  ) {
                targetToken.add(aToken);
            }
        }
        return targetToken.toArray(new Token[targetToken.size()]);
    }

    private List<Token> possiblyHandlePrefixLetter(Token[] srcToken, int i, List<Token> targetToken) {
        Token aToken = srcToken[i];
        // TODO terrible hack to make the unit test pass - don't forget to refactor
        if ((aToken.isNumber())
                && i > 0
                && srcToken[i - 1].isWord()
                && numberPrefixes.contains(srcToken[i - 1].getValue().toLowerCase())
                ) {
            targetToken.set(
                    targetToken.size() - 1,
                    TokenFactory.asMultiTypeToken(targetToken.get(targetToken.size() - 1).getValue() + " " + aToken.getValue()
                    )
            );
            return targetToken;
        }
        return null;
    }
}
package com.company.tokenizer.rule;

import com.company.tokenizer.token.Token;
import com.company.tokenizer.token.TokenFactory;

import java.util.*;

/**
 * Makes a very short string stick to a number
 *
 * Identify if a number has a short string as next element, and if so assumes it is a house number appendix. An example
 * would be "25" + "b" being connected to "25b".
 */
public class HouseNumberStickyRule implements OptimizationRule {

    private static final Set<String> _NUMBER_PREFIXES = new HashSet<>(Arrays.asList(new String[]{"no"}));
    public static final int _MAX_HOUSE_NBR_SUFFIX_LENGTH = 1;

    @Override
    public Token[] optimize(Token[] srcToken) {
        List<Token> targetToken = new ArrayList<>();
        for (int i = 0; i < srcToken.length; i++) {
            Token aToken = srcToken[i];
            // TODO terrible hack to make the unit test pass - don't forget to refactor
            if ( possiblyHandleSuffixLetter(srcToken,i,targetToken) == null
                 &&  possiblyHandlePrefixLetter(srcToken, i, targetToken) == null  ) {
                targetToken.add(aToken);
            }
        }
        return targetToken.toArray(new Token[targetToken.size()]);
    }

    private List<Token> possiblyHandleSuffixLetter(Token[] srcToken, int i, List<Token> targetToken) {
        Token aToken = srcToken[i];
        // TODO terrible hack to make the unit test pass - don't forget to refactor
        if (aToken.isWord()
                && aToken.getValue().length() <= _MAX_HOUSE_NBR_SUFFIX_LENGTH
                && i > 0
                && srcToken[i - 1].isNumber()
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

    private List<Token> possiblyHandlePrefixLetter(Token[] srcToken, int i, List<Token> targetToken) {
        Token aToken = srcToken[i];
        // TODO terrible hack to make the unit test pass - don't forget to refactor
        if ((aToken.isNumber())
                && i > 0
                && srcToken[i - 1].isWord()
                && _NUMBER_PREFIXES.contains(srcToken[i - 1].getValue().toLowerCase())
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
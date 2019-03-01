package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Makes a very short string stick to a number
 *
 * Identify if a number has a short string as next element, and if so assumes it is a house number appendix. An example
 * would be "25" + "b" being connected to "25b".
 */
public class HouseNumberKeywordRule extends AbstractTokenMergingRule {

    private final Set<String> numberPrefixes;

    public HouseNumberKeywordRule(String[] keywords) {
        numberPrefixes = new HashSet<>(Arrays.asList(keywords));
    }

    @Override
    boolean canLastTwoTokensBeMerged(Token[] srcToken, int i, List<Token> targetToken) {
        Token aToken = srcToken[i];
        return  aToken.isNumber()
                && i > 0
                && srcToken[i - 1].isWord()
                && numberPrefixes.contains(srcToken[i - 1].getValue().toLowerCase());
    }
}
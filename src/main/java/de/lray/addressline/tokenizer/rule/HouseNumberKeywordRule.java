package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Makes a very short string stick to a number
 *
 * Identify if a number has a short string as next element, and if so assumes it is a house number appendix. An example
 * would be "25" + "b" being connected to "25b".
 */
public final class HouseNumberKeywordRule extends AbstractTokenMergingRule {

    private final Set<String> numberPrefixes;

    public HouseNumberKeywordRule(String[] keywords) {
        convertToLowerCase(keywords);
        numberPrefixes = new HashSet<>(Arrays.asList(keywords));
        numberPrefixes.remove(null);
    }

    private String[] convertToLowerCase(String[] keywords) {
        for (int i = 0; i < keywords.length; i++) {
            keywords[i] = keywords[i] != null ? keywords[i].toLowerCase() : null;
        }
        return keywords;
    }

    @Override
    protected boolean canLastTwoTokensBeMerged(Token lastToken, Token currentToken) {
        return  currentToken.isNumber()
                && lastToken.isWord()
                && numberPrefixes.contains(lastToken.getValue().toLowerCase());
    }
}
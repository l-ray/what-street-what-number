package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.Token;

/**
 * Makes a very short string stick to a number
 *
 * Identify if a number has a short string as next element, and if so assumes it is a house number appendix. An example
 * would be "25" + "b" being connected to "25b".
 */
public class HouseNumberStickyRule extends AbstractTokenMergingRule {

    public static final int _MAX_HOUSE_NBR_SUFFIX_LENGTH = 1;

    @Override
    boolean canLastTwoTokensBeMerged(Token lastToken, Token currentToken) {
        return currentToken.isWord()
                && currentToken.length() <= _MAX_HOUSE_NBR_SUFFIX_LENGTH
                && lastToken.isNumber();
    }
}
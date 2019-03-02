package de.lray.addressline.parser;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;

public abstract class AbstractParser implements Parser {

    @Override
    public AddressLine parse(Token[] addressToken) {
        addressToken = sanitize(addressToken);
        if (addressToken.length < 2) {
            return null;
        }
        return parseInternal(addressToken);
    }

    abstract AddressLine parseInternal(Token[] addressToken);

    protected Token[] sanitize(Token[] source) {
        Token[] target = new Token[source.length];
        int count = -1;
        for(Token item : source) {
            if(item != null) {
                target[++count] = item;
            }
        }
        return Arrays.copyOf(target, count + 1);
    }

    protected boolean hasStringToken(Token[] tokens) {
        for (Token item : tokens) {
            if ((item.isWord())) {
                return true;
            }
        }
        return false;
    }

    protected boolean isNumberOrMixed(Token token) {
        return token.isNumber();
    }

    protected boolean allFromStringType(Token[] tokens) {
        for (Token item : tokens) {
            if (!(item.isWord())) {
                return false;
            }
        }
        return true;
    }
}

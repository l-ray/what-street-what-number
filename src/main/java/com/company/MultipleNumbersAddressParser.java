package com.company;

import com.company.token.MixedTypeToken;
import com.company.token.NumberToken;
import com.company.token.StringToken;
import com.company.token.Token;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class MultipleNumbersAddressParser implements AddressParseStrategy {

    public Address parse(Token[] addressToken) {
        addressToken = sanitize(addressToken);
        if (addressToken.length < 2) {
            return null;
        }
        Token[] streetCandidates = Arrays.copyOfRange(addressToken, 0, addressToken.length-1);
        Token houseNumberCandidate = addressToken[addressToken.length-1];
        if (hasStringToken(streetCandidates)
                && isNumberOrMixed(houseNumberCandidate)) {
            return new Address(
                    Arrays.stream(streetCandidates).map(Token::getValue).collect( Collectors.joining(" ") ),
                    houseNumberCandidate.getValue()
            );
        }
        return null;
    }

    private Token[] sanitize(Token[] addressToken) {
        return Arrays.stream(addressToken).filter(item -> item != null).toArray(Token[]::new);
    }

    private boolean hasStringToken(Token[] tokens) {
        for (Token item : tokens) {
            if ((item.isWord())) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberOrMixed(Token token) {
        return token.isNumber();
    }
}
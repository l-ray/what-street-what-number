package com.company.rule;

import com.company.dto.Address;
import com.company.tokenizer.token.Token;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NumberFirstParser implements Parser {

    public Address parse(Token[] addressToken) {
        addressToken = sanitize(addressToken);
        if (addressToken.length < 2) {
            return null;
        }
        Token[] streetCandidates = Arrays.copyOfRange(addressToken, 1, addressToken.length);
        Token houseNumberCandidate = addressToken[0];
        if (allFromStringType(streetCandidates)
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


    private boolean allFromStringType(Token[] tokens) {
        for (Token item : tokens) {
            if (!(item.isWord())) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumberOrMixed(Token token) {
        return (token.isNumber());
    }
}
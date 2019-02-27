package com.company;

import com.company.token.MixedTypeToken;
import com.company.token.NumberToken;
import com.company.token.StringToken;
import com.company.token.Token;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NumberFirstAddressParser implements AddressParseStrategy {

    public Address parse(Token[] addressToken) {
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

    private boolean allFromStringType(Token[] tokens) {
        for (Token item : tokens) {
            if (!(item instanceof StringToken)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumberOrMixed(Token token) {
        return (token instanceof NumberToken) || (token instanceof MixedTypeToken);
    }
}
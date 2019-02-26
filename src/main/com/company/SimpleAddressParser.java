package com.company;

import com.company.token.MixedTypeToken;
import com.company.token.NumberToken;
import com.company.token.StringToken;
import com.company.token.Token;

import java.util.Arrays;

public class SimpleAddressParser implements AddressParseStrategy {

    public Address parse(Token[] addressToken) {
        if (allFromStringType(Arrays.copyOfRange(addressToken, 0, addressToken.length-2))
                && isNumberOrMixed(addressToken[addressToken.length-1])) {
            return new Address(addressToken[0].getValue(), addressToken[1].getValue());
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
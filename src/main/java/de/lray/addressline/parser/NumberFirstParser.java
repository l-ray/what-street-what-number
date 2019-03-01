package de.lray.addressline.parser;

import de.lray.addressline.dto.Address;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NumberFirstParser extends AbstractParser {

    public Address parseInternal(Token[] addressToken) {
        Token[] streetCandidates = Arrays.copyOfRange(addressToken, 1, addressToken.length);
        Token houseNumberCandidate = addressToken[0];
        if (allFromStringType(streetCandidates)
                && isNumberOrMixed(houseNumberCandidate)) {
            return new Address(
                    Arrays.stream(streetCandidates).map(Token::getValue).collect(Collectors.joining(" ")),
                    houseNumberCandidate.getValue()
            );
        }
        return null;
    }
}

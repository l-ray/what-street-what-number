package de.lray.addressline.parser;

import de.lray.addressline.dto.Address;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MultipleNumbersParser extends AbstractParser {

    public Address parseInternal(Token[] addressToken) {
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
}
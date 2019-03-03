package de.lray.addressline.parser;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Identifies combinations with the house number being the last word in the token list - as e.g. in German addresses.
 */
public final class SimpleAddressLineParser extends AbstractParser {

    @Override
    protected AddressLine parseInternal(Token[] addressToken) {
        Token[] streetCandidates = Arrays.copyOfRange(addressToken, 0, addressToken.length - 1);
        Token houseNumberCandidate = addressToken[addressToken.length-1];
        if (allFromStringType(streetCandidates)
                && isNumberOrMixed(houseNumberCandidate)) {
            return new AddressLine(
                    Arrays.stream(streetCandidates).map(Token::getValue).collect( Collectors.joining(" ") ),
                    houseNumberCandidate.getValue()
            );
        }
        return null;
    }
}
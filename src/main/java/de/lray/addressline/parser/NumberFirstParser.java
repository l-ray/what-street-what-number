package de.lray.addressline.parser;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Identifies combinations with the house number being the first word in the token list - as e.g. in French addresses.
 */
public final class NumberFirstParser extends AbstractParser {

    @Override
    protected AddressLine parseInternal(Token[] addressToken) {
        Token[] streetCandidates = Arrays.copyOfRange(addressToken, 1, addressToken.length);
        Token houseNumberCandidate = addressToken[0];
        if (allFromStringType(streetCandidates)
                && isNumberOrMixed(houseNumberCandidate)) {
            return new AddressLine(
                    Arrays.stream(streetCandidates).map(Token::getValue).collect(Collectors.joining(" ")),
                    houseNumberCandidate.getValue()
            );
        }
        return null;
    }
}

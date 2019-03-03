package de.lray.addressline.parser;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Handles cases with multiple numbers in the address line, house number being the last word.
 */
public final class MultipleNumbersParser extends AbstractParser {

    @Override
    protected AddressLine parseInternal(Token[] addressToken) {
        Token[] streetCandidates = Arrays.copyOfRange(addressToken, 0, addressToken.length-1);
        Token houseNumberCandidate = addressToken[addressToken.length-1];
        if (hasStringToken(streetCandidates)
                && isNumberOrMixed(houseNumberCandidate)) {
            return new AddressLine(
                    Arrays.stream(streetCandidates).map(Token::getValue).collect( Collectors.joining(" ") ),
                    houseNumberCandidate.getValue()
            );
        }
        return null;
    }
}
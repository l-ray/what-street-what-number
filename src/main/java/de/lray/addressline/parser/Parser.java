package de.lray.addressline.parser;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.tokenizer.token.Token;

public interface Parser {

    /**
     * Return an address string parsed into street and housenumber.
     *
     * @param addressAsToken - address as Token
     * @return an address object being parsed or null if not successful.
     */
    AddressLine parse(Token[] addressAsToken);
}

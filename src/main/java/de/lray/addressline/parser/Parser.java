package de.lray.addressline.parser;

import de.lray.addressline.dto.Address;
import de.lray.addressline.tokenizer.token.Token;

public interface Parser {

    /**
     * Return an address string parsed into street and housenumber.
     *
     * @param addressAsToken - address as Token
     * @return an address object being parsed or null if not successful.
     */
    Address parse(Token[] addressAsToken);
}

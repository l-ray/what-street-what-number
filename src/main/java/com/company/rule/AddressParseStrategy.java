package com.company.rule;

import com.company.dto.Address;
import com.company.tokenizer.token.Token;

public interface AddressParseStrategy {

    /**
     * Return an address string parsed into street and housenumber.
     *
     * @param addressAsToken - address as Token
     * @return an address object being parsed or null if not successful.
     */
    Address parse(Token[] addressAsToken);
}

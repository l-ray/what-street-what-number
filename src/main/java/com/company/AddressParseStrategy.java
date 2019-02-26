package com.company;

import com.company.token.Token;

interface AddressParseStrategy {

    /**
     * Return an address string parsed into street and housenumber.
     *
     * @param addressAsToken - address as Token
     * @return an address object being parsed or null if not successful.
     */
    Address parse(Token[] addressAsToken);
}

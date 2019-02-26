package com.company;

import com.company.token.Token;

import java.util.Iterator;
import java.util.List;

public class AddressParser {
    private static List<AddressParseStrategy> _parser;

    AddressParser(List<AddressParseStrategy> parser) {
        _parser = parser;
    }

    Address parse(Token[] addressAsToken) {
        Address result = null;
        Iterator<AddressParseStrategy> parser = _parser.iterator();
        while (result == null && parser.hasNext()) {
            result = parser.next().parse(addressAsToken);
        }
        return result;
    }

}
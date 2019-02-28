package de.lray.addressline;

import de.lray.addressline.dto.Address;
import de.lray.addressline.parser.Parser;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Iterator;
import java.util.List;

public class Mapper {
    private static List<Parser> _parser;

    Mapper(List<Parser> parser) {
        _parser = parser;
    }

    Address map(Token[] addressAsToken) {
        Address result = null;
        Iterator<Parser> parser = _parser.iterator();
        while (result == null && parser.hasNext()) {
            result = parser.next().parse(addressAsToken);
        }
        return result;
    }

}
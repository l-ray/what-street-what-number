package de.lray.addressline;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.parser.Parser;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Iterator;
import java.util.List;

final class Mapper {
    private static List<Parser> _parser;

    Mapper(List<Parser> parser) {
        _parser = parser;
    }

    AddressLine map(Token[] addressAsToken) {
        AddressLine result = null;
        Iterator<Parser> parser = _parser.iterator();
        while (result == null && parser.hasNext()) {
            result = parser.next().parse(addressAsToken);
        }
        return result;
    }

}
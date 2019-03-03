package de.lray.addressline;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.parser.Parser;
import de.lray.addressline.tokenizer.token.Token;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Map a given order of token (words, numbers, mixed contents) to the fields of a street name and house number.
 *
 * Rules for how the mapping is done, are given as ordered array of concrete parser
 * instances. A given token list will be analyzed by the given parser and in the given order until the first parser is
 * successful and returns a valid address line DTO.
 *
 * In an ETL context, this is a Translator.
 *
 * @see de.lray.addressline.parser.Parser
 * @see de.lray.addressline.dto.AddressLine
 */
final class Mapper {
    private final Parser[] _parser;

    Mapper(Parser[] parser) {
        _parser = parser;
    }

    AddressLine map(Token[] addressAsToken) {
        AddressLine result = null;
        Iterator<Parser> parser = Arrays.asList(_parser).iterator();
        while (result == null && parser.hasNext()) {
            result = parser.next().parse(addressAsToken);
        }
        return result;
    }
}
package de.lray.addressline;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.parser.MultipleNumbersParser;
import de.lray.addressline.parser.NumberFirstParser;
import de.lray.addressline.parser.SimpleAddressLineParser;
import de.lray.addressline.tokenizer.rule.HouseNumberKeywordRule;
import de.lray.addressline.tokenizer.rule.HouseNumberStickyRule;
import de.lray.addressline.tokenizer.token.Token;

import javax.json.JsonObject;
import java.io.PrintStream;
import java.util.Arrays;

public class Main {

    private final Tokenizer _tokenizer;
    private final Mapper _mapper;

    static PrintStream out = System.out;

    Main() {
        _tokenizer = new Tokenizer(
                Arrays.asList(
                        new HouseNumberStickyRule(),
                        new HouseNumberKeywordRule(new String[]{"no"})
                )
        );
        _mapper = new Mapper(
                Arrays.asList(
                        new SimpleAddressLineParser(),
                        new NumberFirstParser(),
                        new MultipleNumbersParser()
                )
        );
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Only one parameter needed.");
        }
        String addressAsString = args[0];
        JsonObject result = new Main().convertStringToJSON(addressAsString);
        out.println(result.toString());
    }

    JsonObject convertStringToJSON(String addressAsString) {
        Token[] addressToken = _tokenizer.tokenize(addressAsString);
        AddressLine result = _mapper.map(addressToken);
        if (result == null) {
            throw new IllegalArgumentException("Argument is not a mappable address :" + addressAsString );
        }
        return Serializer.serialize(result);
    }

}

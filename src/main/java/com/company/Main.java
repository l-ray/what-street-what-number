package com.company;

import com.company.dto.Address;
import com.company.rule.MultipleNumbersParser;
import com.company.rule.NumberFirstParser;
import com.company.rule.SimpleAddressParser;
import com.company.tokenizer.rule.HouseNumberKeywordRule;
import com.company.tokenizer.rule.HouseNumberStickyRule;
import com.company.tokenizer.token.Token;

import javax.json.JsonObject;
import java.util.Arrays;

public class Main {

    private final Tokenizer _tokenizer;
    private final Mapper _mapper;

    Main() {
        _tokenizer = new Tokenizer(
                Arrays.asList(
                        new HouseNumberStickyRule(),
                        new HouseNumberKeywordRule(new String[]{"no"})
                )
        );
        _mapper = new Mapper(
                Arrays.asList(
                        new SimpleAddressParser(),
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
        System.out.println(result.toString());
    }

    JsonObject convertStringToJSON(String addressAsString) {
        Token[] addressToken = _tokenizer.tokenize(addressAsString);
        Address result = _mapper.map(addressToken);
        if (result == null) {
            throw new IllegalArgumentException("Argument is not a mappable address :" + addressAsString );
        }
        return Serializer.serialize(result);
    }

}

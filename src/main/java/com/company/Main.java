package com.company;

import com.company.dto.Address;
import com.company.rule.MultipleNumbersAddressParser;
import com.company.rule.NumberFirstAddressParser;
import com.company.rule.SimpleAddressParser;
import com.company.tokenizer.rule.HouseNumberKeywordRule;
import com.company.tokenizer.rule.HouseNumberStickyRule;
import com.company.tokenizer.rule.OptimizationRule;
import com.company.tokenizer.token.Token;

import javax.json.JsonObject;
import java.util.Arrays;

public class Main {

    private final AddressTokenizer _tokenizer;
    private final AddressParser _parser;

    Main() {
        _tokenizer = new AddressTokenizer(
                Arrays.asList(
                        new HouseNumberStickyRule(),
                        new HouseNumberKeywordRule(new String[]{"no"})
                )
        );
        _parser = new AddressParser(
                Arrays.asList(
                        new SimpleAddressParser(),
                        new NumberFirstAddressParser(),
                        new MultipleNumbersAddressParser()
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
        Address result = _parser.parse(addressToken);
        if (result == null) {
            throw new IllegalArgumentException("Argument is not a mappable address :" + addressAsString );
        }
        return AddressSerializer.serialize(result);
    }

}

package com.company;

import com.company.token.Token;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.Arrays;

public class Main {

    private final AddressTokenizer _tokenizer;
    private final AddressParser _parser;

    Main() {
        _tokenizer = new AddressTokenizer(
                Arrays.asList(new AddressTokenOptimizeStrategy[]{
                        new HouseNumberStickyOptimizerStrategy()
                })
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
        String addressAsString = args[0];
        JsonObject result = new Main().convertStringToJSON(addressAsString);
        System.out.println(result.toString());
    }

    JsonObject convertStringToJSON(String addressAsString) {
        Token[] addressToken = _tokenizer.tokenize(addressAsString);
        Address  result = _parser.parse(addressToken);
        if (result == null) {
            throw new IllegalArgumentException("Argument is not a mappable address :" + addressAsString );
        }
        return AddressSerializer.serialize(result);
    }

}

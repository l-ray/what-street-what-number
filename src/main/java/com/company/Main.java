package com.company;

import com.company.token.Token;

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
                Arrays.asList(new AddressParseStrategy[]{
                        new SimpleAddressParser()
                })
        );
    }

    public static void main(String[] args) {
        String addressAsString = args[0];
        Address result = new Main().convertStringToJSON(addressAsString);
        System.out.println(result.toString());
    }

    Address convertStringToJSON(String addressAsString) {
        Token[] addressToken = _tokenizer.tokenize(addressAsString);
        Address  result = _parser.parse(addressToken);
        return result;
    }

}

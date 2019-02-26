package com.company;

import com.company.token.Token;

import java.util.Arrays;

public class Main {

    private AddressTokenizer tokenizer;
    private AddressParser parser;

    private Main() {
        tokenizer = new AddressTokenizer();
        parser = new AddressParser(
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

    public Address convertStringToJSON(String addressAsString) {
        Token[] addressToken = tokenizer.tokenize(addressAsString);
        //Address  result = parser.parse(addressToken);
        return null;
    }

}

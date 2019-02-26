package com.company;

import com.company.token.Token;
import com.company.token.TokenFactory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class AddressTokenizer {

    private static final List<String> _STOPWORDS = Arrays.asList(",","");
    private static final Predicate<String> _REMOVE_STOPWORDS = item -> !_STOPWORDS.contains(item);

    private static final String _WHITESPACES_AS_REGEX = "[,;]";
    private static final Function<String, String> _CLEAN_FROM_WHITESPACES = item -> item.replaceFirst(_WHITESPACES_AS_REGEX, "");
    private static final Function<String, Token> _STRING_TO_TOKEN = item -> TokenFactory.asToken(item);

    AddressTokenizer(){
        // nothing to be added yet.
    }

    Token[] tokenize(String addressString) {
        String[] splitString = addressString.split(" ");
        Token[] token = Arrays
                .stream(splitString)
                .filter(_REMOVE_STOPWORDS)
                .map(_CLEAN_FROM_WHITESPACES)
                .map(_STRING_TO_TOKEN)
                .toArray(size -> new Token[size]);
        return token;
    }


}

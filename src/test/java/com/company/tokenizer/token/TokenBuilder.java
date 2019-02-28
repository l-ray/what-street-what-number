package com.company.tokenizer.token;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper to be able to keep the token package-accessible only.
 */
public class TokenBuilder {

    private List<Token> result;

    public TokenBuilder() {
        result = new ArrayList<>();
    }

    public TokenBuilder addWord(String input) {
        result.add(new StringToken(input));
        return this;
    }

    public TokenBuilder addNumber(String input) {
        result.add(new NumberToken(input));
        return this;
    }

    public TokenBuilder addMixed(String input) {
        result.add(new MixedTypeToken(input));
        return this;
    }

    public Token[] build() {
        return result.toArray(new Token[result.size()]);
    }
}

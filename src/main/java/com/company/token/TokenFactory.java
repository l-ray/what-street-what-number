package com.company.token;

/**
 * Created by lray on 25.02.19.
 */
public abstract class TokenFactory {
    public static Token asToken(String input) {

        if (input.matches("[a-zA-ZöäüÖÄÜß-]*")) {
            return new StringToken(input);
        }

        if (input.matches("[0-9]*")) {
            return new NumberToken(input);
        }

        if (input.matches("[0-9a-zA-Z]*")) {
            return new MixedTypeToken((input));
        }
        throw new IllegalArgumentException("Could not tokenize input"+input);
    }
}

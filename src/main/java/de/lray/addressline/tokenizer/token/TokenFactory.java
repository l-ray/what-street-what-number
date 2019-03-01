package de.lray.addressline.tokenizer.token;

public abstract class TokenFactory {
    /**
     * Returns fitting token based on the input string.
     * @param input - String without whitespaces
     * @return token presenting content, adding character information about it.
     */
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

    public static Token asMultiTypeToken(String input) {
        return new MixedTypeToken(input);
    }
}

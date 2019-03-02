package de.lray.addressline.tokenizer.token;

import java.util.regex.Pattern;

public abstract class TokenFactory {

    private static final Pattern _WORDS_PATTERN = Pattern.compile("[a-zA-ZöäüÖÄÜß-]+");
    private static final Pattern _NUMBER_PATTERN = Pattern.compile("[0-9]+");
    private static final Pattern _MIXED_TYPE_PATTERN = Pattern.compile("[0-9a-zA-Z]+");

    /**
     * Returns fitting token based on the input string.
     * @param input - String without whitespaces
     * @return token presenting content, adding character information about it.
     */
    public static Token asToken(String input) {

        if (_WORDS_PATTERN.matcher(input).matches()) {
            return new StringToken(input);
        }

        if (_NUMBER_PATTERN.matcher(input).matches()) {
            return new NumberToken(input);
        }

        if (_MIXED_TYPE_PATTERN.matcher(input).matches()) {
            return new MixedTypeToken((input));
        }
        throw new IllegalArgumentException("Could not tokenize input"+input);
    }

    public static Token asMultiTypeToken(String input) {
        return new MixedTypeToken(input);
    }
}

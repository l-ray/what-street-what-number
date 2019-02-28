package de.lray.addressline.tokenizer.token;

/**
 * Holds common functionality for all immutable token.
 */
abstract class AbstractToken implements Token {
    private final String value;

    protected AbstractToken(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean isWord() {
        return false;
    }

    @Override
    public boolean isNumber() {
        return false;
    }
}

package de.lray.addressline.tokenizer.token;

/**
 * Token represents a pure word as a string.
 */
public final class StringToken extends AbstractToken {

    StringToken(String value) {
        super(value);
    }

    @Override
    public boolean isWord() { return true; }

    @Override
    public String toString() {
        return "StringToken |" + getValue() + "|";
    }
}

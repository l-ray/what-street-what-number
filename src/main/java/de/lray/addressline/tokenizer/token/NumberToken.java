package de.lray.addressline.tokenizer.token;

/**
 * Token representing a number, possibly a house-number. Be aware, that any number holding a comma/dot will be
 * represented as a string token instead.
 */
public final class NumberToken extends AbstractToken {

    NumberToken(String value) {
        super(value);
    }

    @Override
    public boolean isNumber() { return true; }

    @Override
    public String toString() {
        return "NumberToken |" + getValue() + "|";
    }
}

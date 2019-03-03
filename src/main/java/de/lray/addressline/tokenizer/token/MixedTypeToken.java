package de.lray.addressline.tokenizer.token;

/**
 * Token representing everything that is not a clear string or a clear number.
 */
public final class MixedTypeToken extends AbstractToken {

    MixedTypeToken(String value) {
        super(value);
    }

    @Override
    public boolean isNumber() { return true; }

    @Override
    public boolean isWord() { return true; }

    @Override
    public String toString() {
        return "MixedTypeToken |" + getValue() + "|";
    }

}

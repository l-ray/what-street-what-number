package com.company.token;

/**
 * Created by lray on 25.02.19.
 */
public class StringToken extends AbstractToken {

    public StringToken(String value) {
        super(value);
    }

    @Override
    public boolean isWord() { return true; }

    @Override
    public String toString() {
        return "StringToken |" + getValue() + "|";
    }
}

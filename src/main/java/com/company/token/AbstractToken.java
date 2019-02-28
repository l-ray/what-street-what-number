package com.company.token;

/**
 * Created by lray on 28.02.19.
 */
public class AbstractToken implements Token {
    private final String value;

    public AbstractToken(String value) {
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

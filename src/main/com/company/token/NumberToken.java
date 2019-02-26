package com.company.token;

/**
 * Created by lray on 25.02.19.
 */
public class NumberToken implements Token {

    private final String value;

    public NumberToken(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

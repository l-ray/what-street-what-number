package com.company.token;

/**
 * Created by lray on 25.02.19.
 */
public class StringToken implements Token {

    private final String value;

    public StringToken(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

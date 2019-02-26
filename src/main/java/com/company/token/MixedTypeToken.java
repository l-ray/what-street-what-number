package com.company.token;

/**
 * Created by lray on 25.02.19.
 */
public class MixedTypeToken implements Token {

    private final String value;

    public MixedTypeToken(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MixedTypeToken |"+value+"|";
    }

}

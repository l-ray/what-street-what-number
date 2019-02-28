package com.company.token;

/**
 * Created by lray on 25.02.19.
 */
public class NumberToken extends AbstractToken {

    public NumberToken(String value) {
        super(value);
    }

    @Override
    public boolean isNumber() { return true; }

    @Override
    public String toString() {
        return "NumberToken |"+getValue()+"|";
    }
}

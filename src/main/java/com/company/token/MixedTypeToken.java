package com.company.token;

/**
 * Created by lray on 25.02.19.
 */
public class MixedTypeToken extends AbstractToken {

    public MixedTypeToken(String value) {
        super(value);
    }

    @Override
    public boolean isNumber() { return true; }

    @Override
    public boolean isWord() { return true; }

    @Override
    public String toString() {
        return "MixedTypeToken |"+getValue()+"|";
    }

}

package com.company.token;

/**
 * Created by lray on 25.02.19.
 */
public interface Token {
    String getValue();
    boolean isWord();
    boolean isNumber();
}

package de.lray.addressline.tokenizer.token;

/**
 * A word, number or mixed content type (which is marked as word/token at the same time).
 */
public interface Token {
    String getValue();
    boolean isWord();
    boolean isNumber();
    int length();
}

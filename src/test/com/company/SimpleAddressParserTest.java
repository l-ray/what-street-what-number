package com.company;

import com.company.token.NumberToken;
import com.company.token.StringToken;
import com.company.token.Token;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleAddressParserTest {

    @Test
    public void oneWordOneNumber() throws Exception {
        // Given
        Token[] input = new Token[]{
                new StringToken("Winterallee"),
                new NumberToken("3")
        };
        // When
        Address result = new SimpleAddressParser().parse(input);
        // Then
        assertNotNull(result);
    }

    @Test
    public void multipleWordsOneNumber() throws Exception {
        // Given
        Token[] input = new Token[]{
                new StringToken("Am"),
                new StringToken("Bächle"),
                new NumberToken("3")
        };
        // When
        Address result = new SimpleAddressParser().parse(input);
        // Then
        assertNotNull(result);
    }
}
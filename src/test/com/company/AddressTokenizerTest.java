package com.company;

import com.company.token.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTokenizerTest {

    @Test
    public void tokenizesPrimitives() throws Exception {
        // Given
        AddressTokenizer underTest = new AddressTokenizer();
        // When / Then
        assertTrue(underTest.tokenize("String")[0] instanceof StringToken);
        assertTrue(underTest.tokenize("42")[0] instanceof NumberToken);
        assertTrue(underTest.tokenize("2A")[0] instanceof MixedTypeToken);
    }


    @Test
    public void tokenizesTypes() throws Exception {
        // Given
        String input = "Is 21 15h";
        // When
        Token[] result = new AddressTokenizer().tokenize(input);
        // Then
        assertTrue(result[0] instanceof StringToken);
        assertTrue(result[1] instanceof NumberToken);
        assertTrue(result[2] instanceof MixedTypeToken);
    }

    @Test
    public void eliminatesStoppWords() throws Exception {
        // Given
        String input = "200 , Berchtesgaden ";
        // When
        Token[] result = new AddressTokenizer().tokenize(input);
        // Then
        assertEquals("token-Length of "+input, 2, result.length);
        assertTrue(result[0] instanceof NumberToken);
        assertTrue(result[1] instanceof StringToken);
    }

    @Test
    public void cleansNumberTokenFromClutter() throws Exception {
        // Given
        String input = "4, rue;";
        // When
        Token[] result = new AddressTokenizer().tokenize(input);
        // Then
        assertEquals("token-Length of "+input, 2, result.length);
        assertTrue(result[0] instanceof NumberToken);
        assertTrue(result[1] instanceof StringToken);
    }
}
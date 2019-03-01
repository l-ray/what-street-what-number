package de.lray.addressline.tokenizer.token;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenTest {

    @Test
    public void testWordToken() throws Exception {
        // Given
        final String input = "word";
        // When
        Token token = new StringToken(input);
        // Then
        assertTrue(token.isWord());
        assertFalse(token.isNumber());
        assertEquals(token.getValue(), input);
        assertEquals(token.length(), input.length());
        assertThat(token.toString(),CoreMatchers.containsString("String"));
        assertThat(token.toString(),CoreMatchers.containsString(input));
    }

    @Test
    public void testNumberToken() throws Exception {
        // Given
        final String input = "5";
        // When
        Token token = new NumberToken(input);
        // Then
        assertFalse(token.isWord());
        assertTrue(token.isNumber());
        assertEquals(token.getValue(), input);
        assertEquals(token.length(), input.length());
        assertThat(token.toString(),CoreMatchers.containsString("Number"));
        assertThat(token.toString(),CoreMatchers.containsString(input));
    }

    @Test
    public void testMixedToken() throws Exception {
        // Given
        final String input = "5bc";
        // When
        Token token = new MixedTypeToken(input);
        // Then
        assertTrue(token.isWord());
        assertTrue(token.isNumber());
        assertEquals(token.getValue(), input);
        assertEquals(token.length(), input.length());
        assertThat(token.toString(),CoreMatchers.containsString("Mixed"));
        assertThat(token.toString(),CoreMatchers.containsString(input));
    }

    @Test
    public void handlesNullToken() throws Exception {
        assertEquals(-1, new StringToken(null).length());
        assertEquals(-1, new NumberToken(null).length());
        assertEquals(-1, new MixedTypeToken(null).length());
    }
}
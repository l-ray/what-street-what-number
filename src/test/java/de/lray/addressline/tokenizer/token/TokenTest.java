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
        assertThat(token.toString(),CoreMatchers.containsString("String"));
        assertThat(token.toString(),CoreMatchers.containsString(input));
    }

    @Test
    public void testNumberToken() throws Exception {
        final String input = "5";
        Token token = new NumberToken(input);
        assertFalse(token.isWord());
        assertTrue(token.isNumber());
        assertEquals(token.getValue(), input);
        assertThat(token.toString(),CoreMatchers.containsString("Number"));
        assertThat(token.toString(),CoreMatchers.containsString(input));
    }

    @Test
    public void testMixedToken() throws Exception {
        final String input = "5bc";
        Token token = new MixedTypeToken(input);
        assertTrue(token.isWord());
        assertTrue(token.isNumber());
        assertEquals(token.getValue(), input);
        assertThat(token.toString(),CoreMatchers.containsString("Mixed"));
        assertThat(token.toString(),CoreMatchers.containsString(input));
    }
}
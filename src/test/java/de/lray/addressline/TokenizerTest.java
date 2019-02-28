package de.lray.addressline;

import de.lray.addressline.tokenizer.token.NumberToken;
import de.lray.addressline.tokenizer.token.StringToken;
import de.lray.addressline.tokenizer.token.MixedTypeToken;
import de.lray.addressline.tokenizer.token.Token;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TokenizerTest {

    private Tokenizer underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new Tokenizer(Collections.emptyList());
    }

    @Test
    public void tokenizesPrimitives() throws Exception {
        // Given / When / Then
        assertTrue(underTest.tokenize("String")[0] instanceof StringToken);
        assertTrue(underTest.tokenize("42")[0] instanceof NumberToken);
        assertTrue(underTest.tokenize("2A")[0] instanceof MixedTypeToken);
    }


    @Test
    public void tokenizesTypes() throws Exception {
        // Given
        String input = "Is 21 15h";
        // When
        Token[] result = underTest.tokenize(input);
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
        Token[] result = underTest.tokenize(input);
        // Then
        assertEquals("token-Length of "+input, 2, result.length);
        assertTrue(result[0] instanceof NumberToken);
        assertTrue(result[1] instanceof StringToken);
    }

    @Test
    public void eliminateSpacesFromString() {
        // Given
        String input = "   Im    Winkel    48b  ";
        // When
        Token[] result = underTest.tokenize(input);
        // Then
        assertEquals("token-Length of "+input, 3, result.length);
        for (Token item : result) {
            assertTrue(item.getValue().length() > 0);
            assertEquals(
                    "Token correctly trimmed",
                    item.getValue(),
                    item.getValue().trim()
            );
        }
    }

    @Test
    public void cleansNumberTokenFromClutter() throws Exception {
        // Given
        String input = "4, rue;";
        // When
        Token[] result = underTest.tokenize(input);
        // Then
        assertEquals("token-Length of "+input, 2, result.length);
        assertTrue(result[0] instanceof NumberToken);
        assertTrue(result[1] instanceof StringToken);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tokenizeNullString() {
        underTest.tokenize(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tokenizeSpecialCharString() {
        underTest.tokenize("23ü");
    }
}
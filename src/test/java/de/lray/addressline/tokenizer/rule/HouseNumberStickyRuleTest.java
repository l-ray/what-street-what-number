package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.MixedTypeToken;
import de.lray.addressline.tokenizer.token.StringToken;
import de.lray.addressline.tokenizer.token.Token;
import de.lray.addressline.tokenizer.token.TokenBuilder;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HouseNumberStickyRuleTest {

    private OptimizationRule underTest = new HouseNumberStickyRule();

    @Test
    public void combineAlphanumericHouseNumbersWithSpace() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addWord("Vogelwiese")
                .addNumber("23")
                .addWord("b")
                .build();
        // When
        Token[] result = underTest.optimize(input);
        // Then
        assertEquals("token-Length of "+Arrays.asList(input), 2, result.length);
        assertTrue(result[0] instanceof StringToken);
        assertTrue(result[1] instanceof MixedTypeToken);
        assertEquals("Original space is preserved", result[1].getValue(), "23 b");
    }

    @Test
    public void combineMultipleAlphanumericHouseNumbersWithSpace() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addNumber("23")
                .addWord("c")
                .addWord("Vogelwiese")
                .addNumber("23")
                .addWord("b")
                .build();
        // When
        Token[] result = underTest.optimize(input);
        // Then
        assertEquals("token-Length of "+Arrays.asList(input), 3, result.length);
        assertTrue(result[0] instanceof MixedTypeToken);
        assertTrue(result[1] instanceof StringToken);
        assertTrue(result[2] instanceof MixedTypeToken);
        assertEquals("Original space is preserved", result[0].getValue(), "23 c");
        assertEquals("Original space is preserved", result[2].getValue(), "23 b");
    }

    @Test
    public void leaveShortAlphanumericsUntouched() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addWord("A")
                .addWord("b")
                .addWord("c")
                .build();
        // When
        Token[] result = underTest.optimize(input);
        // Then
        assertEquals("token-Length of "+Arrays.asList(input), 3, result.length);
    }
}
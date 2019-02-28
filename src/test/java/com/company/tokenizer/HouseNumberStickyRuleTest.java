package com.company.tokenizer;

import com.company.tokenizer.token.*;
import com.company.tokenizer.rule.HouseNumberStickyRule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HouseNumberStickyRuleTest {

    @Test
    public void combineAlphanumericHouseNumbersWithSpace() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addWord("Vogelwiese")
                .addNumber("23")
                .addWord("b")
                .build();
        // When
        Token[] result = new HouseNumberStickyRule().optimize(input);
        // Then
        assertEquals("token-Length of "+Arrays.asList(input), 2, result.length);
        assertTrue(result[0] instanceof StringToken);
        assertTrue(result[1] instanceof MixedTypeToken);
        assertEquals("Original space is preserved", result[1].getValue(), "23 b");
    }

    @Test
    public void combineStopWordAndHouseNumbers() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addWord("Calle")
                .addWord("No")
                .addNumber("1540")
                .build();
        // When
        Token[] result = new HouseNumberStickyRule().optimize(input);
        // Then
        assertEquals("token-Length of "+ Arrays.asList(input), 2, result.length);
        assertTrue(result[0] instanceof StringToken);
        assertTrue(result[1] instanceof MixedTypeToken);
        assertEquals("Original space is preserved", result[1].getValue(), "No 1540");
    }

}
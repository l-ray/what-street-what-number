package com.company.tokenizer.rule;

import com.company.tokenizer.token.MixedTypeToken;
import com.company.tokenizer.token.StringToken;
import com.company.tokenizer.token.Token;
import com.company.tokenizer.token.TokenBuilder;
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
}
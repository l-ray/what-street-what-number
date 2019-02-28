package com.company.tokenizer.rule;

import com.company.tokenizer.token.MixedTypeToken;
import com.company.tokenizer.token.StringToken;
import com.company.tokenizer.token.Token;
import com.company.tokenizer.token.TokenBuilder;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HouseNumberKeywordRuleTest {

    private OptimizationRule underTest = new HouseNumberKeywordRule(new String[]{"app"});

    @Test
    public void combineStopWordAndHouseNumbers() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addWord("Calle")
                .addWord("App")
                .addNumber("1540")
                .build();
        // When
        Token[] result = underTest.optimize(input);
        // Then
        assertEquals("token-Length of "+ Arrays.asList(input), 2, result.length);
        assertTrue(result[0] instanceof StringToken);
        assertTrue(result[1] instanceof MixedTypeToken);
        assertEquals("Original space is preserved", result[1].getValue(), "App 1540");
    }
}
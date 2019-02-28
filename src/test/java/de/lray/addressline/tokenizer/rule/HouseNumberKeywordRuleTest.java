package de.lray.addressline.tokenizer.rule;

import de.lray.addressline.tokenizer.token.MixedTypeToken;
import de.lray.addressline.tokenizer.token.StringToken;
import de.lray.addressline.tokenizer.token.Token;
import de.lray.addressline.tokenizer.token.TokenBuilder;
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
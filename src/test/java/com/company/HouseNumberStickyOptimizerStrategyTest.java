package com.company;

import com.company.token.MixedTypeToken;
import com.company.token.NumberToken;
import com.company.token.StringToken;
import com.company.token.Token;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by lray on 26.02.19.
 */
public class HouseNumberStickyOptimizerStrategyTest {

    @Test
    public void combineAlphanumericHouseNumbersWithSpace() throws Exception {
        // Given
        Token[] input = new Token[]{
                new StringToken("Vogelwiese"),
                new NumberToken("23"),
                new StringToken("b")
        };
        // When
        Token[] result = new HouseNumberStickyOptimizerStrategy().optimize(input);
        // Then
        assertEquals("token-Length of "+Arrays.asList(input), 2, result.length);
        assertTrue(result[0] instanceof StringToken);
        assertTrue(result[1] instanceof MixedTypeToken);
        assertEquals("Original space is preserved", result[1].getValue(), "23 b");
    }

    @Test
    public void combineStopWordAndHouseNumbers() throws Exception {
        // Given
        Token[] input = new Token[]{
                new StringToken("Calle"),
                new StringToken("No"),
                new NumberToken("1540")
        };
        // When
        Token[] result = new HouseNumberStickyOptimizerStrategy().optimize(input);
        // Then
        assertEquals("token-Length of "+ Arrays.asList(input), 2, result.length);
        assertTrue(result[0] instanceof StringToken);
        assertTrue(result[1] instanceof MixedTypeToken);
        assertEquals("Original space is preserved", result[1].getValue(), "No 1540");
    }

}
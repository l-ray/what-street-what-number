package com.company;

import com.company.token.MixedTypeToken;
import com.company.token.NumberToken;
import com.company.token.StringToken;
import com.company.token.Token;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NumberFirstAddressParserTest {

    private AddressParseStrategy underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new NumberFirstAddressParser();
    }

    @Test
    public void oneWordOneNumber() throws Exception {
        // Given
        Token[] input = new Token[]{
                new NumberToken("5"),
                new StringToken("Broadway"),
        };
        // When
        Address result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }

    @Test
    public void oneWordOneMixedType() throws Exception {
        // Given
        Token[] input = new Token[]{
                new MixedTypeToken("5b"),
                new StringToken("Broadway"),
        };
        // When
        Address result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }

    @Test
    public void multipleWordsOneNumber() throws Exception {
        // Given
        Token[] input = new Token[]{
                new NumberToken("200"),
                new StringToken("Broadway"),
                new StringToken("Ave")
        };
        // When
        Address result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }

    @Test
    public void emptyParameter() throws Exception {
        // Given / When / Then
        assertNull(underTest.parse(new Token[0]));
    }

    @Test
    public void nullParameter() throws Exception {
        // Given / When / Then
        assertNull(underTest.parse(new Token[]{null}));
        assertNull(underTest.parse(new Token[]{null,null}));
        assertNull(underTest.parse(new Token[]{null,null,null}));
    }
}
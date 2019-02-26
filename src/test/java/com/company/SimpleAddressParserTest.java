package com.company;

import com.company.token.MixedTypeToken;
import com.company.token.NumberToken;
import com.company.token.StringToken;
import com.company.token.Token;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleAddressParserTest {

    private SimpleAddressParser underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new SimpleAddressParser();
    }

    @Test
    public void oneWordOneNumber() throws Exception {
        // Given
        Token[] input = new Token[]{
                new StringToken("Winterallee"),
                new NumberToken("3")
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
                new StringToken("Blaufeldweg"),
                new MixedTypeToken("123B")
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
                new StringToken("Am"),
                new StringToken("Bächle"),
                new NumberToken("3")
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
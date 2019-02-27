package com.company;

import com.company.token.MixedTypeToken;
import com.company.token.NumberToken;
import com.company.token.StringToken;
import com.company.token.Token;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MultipleNumbersAddressParserTest {

    private AddressParseStrategy underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new MultipleNumbersAddressParser();
    }

    @Test
    public void oneWordOneNumberOneMixedType() throws Exception {
        // Given
        Token[] input = new Token[]{
                new StringToken("Calle"),
                new NumberToken("39"),
                new MixedTypeToken("No 1540")
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
                new StringToken("Calle"),
                new StringToken("du"),
                new NumberToken("15"),
                new MixedTypeToken("No 1540")
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
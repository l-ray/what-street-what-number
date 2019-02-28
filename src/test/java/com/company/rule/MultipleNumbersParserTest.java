package com.company.rule;

import com.company.dto.Address;
import com.company.tokenizer.token.Token;
import com.company.tokenizer.token.TokenBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MultipleNumbersParserTest extends AbstractParserTest {

    @Before
    public void setUp() throws Exception {
        underTest = new MultipleNumbersParser();
    }

    @Test
    public void oneWordOneNumberOneMixedType() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addWord("Calle")
                .addNumber("39")
                .addMixed("No 1540")
                .build();
        // When
        Address result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }

    @Test
    public void multipleWordsOneNumber() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addWord("Calle")
                .addWord("du")
                .addNumber("15")
                .addMixed("No 1540")
                .build();
        // When
        Address result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }
}
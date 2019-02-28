package com.company.rule;

import com.company.dto.Address;
import com.company.tokenizer.token.Token;
import com.company.tokenizer.token.TokenBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NumberFirstAddressParserTest extends AbstractRuleTest {

    @Before
    public void setUp() throws Exception {
        underTest = new NumberFirstAddressParser();
    }

    @Test
    public void oneWordOneNumber() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addNumber("5")
                .addWord("Broadway")
                .build();
        // When
        Address result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }

    @Test
    public void oneWordOneMixedType() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addMixed("5b")
                .addWord("Broadway")
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
                .addNumber("200")
                .addWord("Broadway")
                .addWord("Ave")
                .build();
        // When
        Address result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }
}
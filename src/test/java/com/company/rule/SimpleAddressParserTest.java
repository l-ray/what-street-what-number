package com.company.rule;

import com.company.dto.Address;
import com.company.rule.AddressParseStrategy;
import com.company.rule.SimpleAddressParser;
import com.company.tokenizer.token.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleAddressParserTest extends AbstractRuleTest {

    @Before
    public void setUp() throws Exception {
        underTest = new SimpleAddressParser();
    }

    @Test
    public void oneWordOneNumber() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addWord("Winterallee")
                .addNumber("3")
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
                .addWord("Blaufeldweg")
                .addMixed("123B")
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
                .addWord("Am")
                .addWord("Bächle")
                .addNumber("3")
                .build();
        // When
        Address result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }
}
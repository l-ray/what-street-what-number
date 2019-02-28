package com.company.rule;

import com.company.dto.Address;
import com.company.rule.AddressParseStrategy;
import com.company.rule.MultipleNumbersAddressParser;
import com.company.tokenizer.token.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MultipleNumbersAddressParserTest extends AbstractRuleTest {

    @Before
    public void setUp() throws Exception {
        underTest = new MultipleNumbersAddressParser();
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
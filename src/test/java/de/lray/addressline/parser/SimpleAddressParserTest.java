package de.lray.addressline.parser;

import de.lray.addressline.dto.Address;
import de.lray.addressline.tokenizer.token.Token;
import de.lray.addressline.tokenizer.token.TokenBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SimpleAddressParserTest extends AbstractParserTest {

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
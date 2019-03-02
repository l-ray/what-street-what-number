package de.lray.addressline.parser;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.tokenizer.token.Token;
import de.lray.addressline.tokenizer.token.TokenBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NumberFirstParserTest extends AbstractParserTest {

    @Before
    public void setUp() throws Exception {
        underTest = new NumberFirstParser();
    }

    @Test
    public void oneWordOneNumber() throws Exception {
        // Given
        Token[] input = new TokenBuilder()
                .addNumber("5")
                .addWord("Broadway")
                .build();
        // When
        AddressLine result = underTest.parse(input);
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
        AddressLine result = underTest.parse(input);
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
        AddressLine result = underTest.parse(input);
        // Then
        assertNotNull(result);
    }
}
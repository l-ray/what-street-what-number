package de.lray.addressline;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.parser.Parser;
import de.lray.addressline.tokenizer.rule.OptimizationRule;
import de.lray.addressline.tokenizer.token.Token;
import org.junit.Test;

import javax.json.JsonObject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AddressLineConverterTest {

    private static Tokenizer mockTokenizer = new Tokenizer(new OptimizationRule[0]);
    private static Mapper mockMapper = new Mapper(new Parser[0]);

    @Test(expected = NullPointerException.class)
    public void testNullConstructor() {
        new AddressLineConverter(null, null);
    }

    @Test
    public void initializesConstructor(){
        // Given / When
        AddressLineConverter c = new AddressLineConverter(
                mockTokenizer,
                mockMapper
        );
        // Then
        assertNotNull(c);
    }

    @Test(expected = IllegalArgumentException.class)
    public void handleNonParseableInput(){
        // Given
        AddressLineConverter c = new AddressLineConverter(
                mockTokenizer,
                mockMapper
        );
        // When
        c.asJSON("There are no parser connected");
    }

    @Test
    public void callsMapper(){
        // Given
        MockParser mockParser = new MockParser();
        // When
        AddressLineConverter c = new AddressLineConverter(
                mockTokenizer,
                new Mapper(new Parser[]{mockParser})
        );
        JsonObject result = c.asJSON("Test Test");
        // Then
        assertTrue(mockParser.mapperCalled);
        assertNotNull(result);
    }

    private class MockParser implements Parser {
        public boolean mapperCalled = false;
        @Override
        public AddressLine parse(Token[] addressAsToken) {
            mapperCalled = true;
            return new AddressLine("Lore ipsum", "42");
        }
    }
}
package de.lray.addressline;

import de.lray.addressline.dto.AddressLine;
import de.lray.addressline.parser.Parser;
import de.lray.addressline.tokenizer.token.Token;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapperTest {

    @Test
    public void emptyParserList() throws Exception {
        Mapper underTest = new Mapper(new Parser[0]);
        assertNull(underTest.map(new Token[0]));
    }

    @Test
    public void parserExecuted() throws Exception {
        MockParser firstParser = new MockParser();
        MockParser secondParser = new MockParser();
        Mapper underTest = new Mapper(new Parser[]{
                firstParser, secondParser
        });
        assertNull(underTest.map(new Token[0]));
        assertTrue(firstParser.called);
        assertTrue(secondParser.called);
    }

    @Test
    public void parserExecutedInGivenOrderStoppingAfterSucess() throws Exception {
        MockParser firstParser = new MockParser();
        MockParser secondParser = new MockParser(){
            @Override
            protected AddressLine giveResult() {
                return new AddressLine("one","two");
            }
        };
        MockParser thirdParser = new MockParser();
        Mapper underTest = new Mapper(new Parser[]{
                firstParser, secondParser, thirdParser
        });
        assertNotNull(underTest.map(new Token[0]));
        assertTrue(firstParser.called);
        assertTrue(secondParser.called);
        assertFalse(thirdParser.called);
    }

    private class MockParser implements Parser {
        boolean called = false;

        @Override
        public AddressLine parse(Token[] addressAsToken) {
            called = true;
            return giveResult();
        }

        protected AddressLine giveResult() {
            return null;
        }
    }
}
package de.lray.addressline.parser;

import de.lray.addressline.tokenizer.token.Token;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Test default answers all rules should have in common.
 */
abstract class AbstractParserTest {

    protected Parser underTest;

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

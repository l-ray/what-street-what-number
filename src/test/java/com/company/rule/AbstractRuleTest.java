package com.company.rule;

import com.company.tokenizer.token.Token;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Test default answers all rules should have in common.
 */
abstract class AbstractRuleTest {

    protected AddressParseStrategy underTest;

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

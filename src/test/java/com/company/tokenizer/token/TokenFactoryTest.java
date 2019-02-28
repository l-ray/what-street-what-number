package com.company.tokenizer.token;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lray on 25.02.19.
 */
public class TokenFactoryTest {

    @Test
    public void recognizesString() throws Exception {
        assertTrue(TokenFactory.asToken("SingleWord") instanceof StringToken);
    }

    @Test
    public void recognizesStringWithUmlauts() throws Exception {
        // Given
        String[] candidates = new String[]{"Bächle", "Glück", "Römer", "Über", "Öhrling", "Ämber"};
        for (String item : candidates) {
            Token result = TokenFactory.asToken(item);
            assertTrue(item,  result instanceof StringToken);
        }
    }

    @Test
    public void recognizesStringWithSpecialChar() throws Exception {
        assertTrue("hyphen",TokenFactory.asToken("Eduard-von-Wintherstein") instanceof StringToken);
        assertTrue("sharp S",TokenFactory.asToken("Fußmatte") instanceof StringToken);
    }

    @Test
    public void recognizesNumber() throws Exception {
        assertTrue(TokenFactory.asToken("1980") instanceof NumberToken);
    }

    @Test
    public void recognizesMixedContent() throws Exception {
        assertTrue(TokenFactory.asToken("15b") instanceof MixedTypeToken);
    }

}
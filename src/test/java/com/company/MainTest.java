package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lray on 26.02.19.
 */
public class MainTest {

    @Test
    public void testMain() throws Exception {

        assertEquals(
                convert("Winterallee 3"),
                "{\"street\":\"Winterallee\",\"housenumber\":\"3\"}"
        );
        assertEquals(
                convert("Musterstrasse 45"),
                "{\"street\":\"Musterstrasse\",\"housenumber\":\"45\"}"
        );
        assertEquals(
                convert("Blaufeldweg 123B"),
                "{\"street\":\"Blaufeldweg\",\"housenumber\":\"123B\"}"
        );
        assertEquals(
                convert("Am Bächle 23"),
                "{\"street\":\"Am Bächle\",\"housenumber\":\"23\"}"
        );
        assertEquals(
                convert("Auf der Vogelwiese 23 b"),
                "{\"street\":\"Auf der Vogelwiese\",\"housenumber\":\"23 b\"}"
        );
        assertEquals(
                convert("4, rue de la revolution"),
                "{\"street\":\"rue de la revolution\",\"housenumber\":\"4\"}"
        );
        assertEquals(
                convert("200 Broadway Av"),
                "{\"street\":\"Broadway Av\",\"housenumber\":\"200\"}"
        );
        assertEquals(
                convert("Calle Aduana, 29"),
                "{\"street\":\"Calle Aduana\",\"housenumber\":\"29\"}"
        );
        assertEquals(
                convert("Calle 39 No 1540"),"{\"street\":\"Calle 39\",\"housenumber\":\"No 1540\"}"
        );
    }
    
    private String convert(String input) {
        return new Main().convertStringToJSON(input).toString();
    }
}
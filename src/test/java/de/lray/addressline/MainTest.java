package de.lray.addressline;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        Main.out = new PrintStream(outContent);
    }

    @After
    public void tearDown() {
        Main.out = System.out;
    }

    @Test(expected = IllegalArgumentException.class)
    public void runsStaticVoidMainWithoutParameter() {
        Main.main(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void runsStaticVoidMainWithBogusAddressParameter() {
        Main.main(new String[]{"fkjdsl@fdjslk1 13@"});
    }

    @Test
    public void runsStaticVoidMainHappyPath() {
        // Given / When
        Main.main(new String[]{"Bach 123"});
        // Then
        assertEquals("{\"street\":\"Bach\",\"housenumber\":\"123\"}\n", outContent.toString());
    }
    @Test
    public void simpleAddressExamples() throws Exception {

        assertAddressMapping(
                "Winterallee 3",
                "{\"street\":\"Winterallee\",\"housenumber\":\"3\"}"
        );
        assertAddressMapping(
                "Musterstrasse 45",
                "{\"street\":\"Musterstrasse\",\"housenumber\":\"45\"}"
        );
        assertAddressMapping(
                "Blaufeldweg 123B",
                "{\"street\":\"Blaufeldweg\",\"housenumber\":\"123B\"}"
        );
    }

    @Test
    public void complexAddressExamples() throws Exception {

        assertAddressMapping(
                "Am Bächle 23",
                "{\"street\":\"Am Bächle\",\"housenumber\":\"23\"}"
        );
        assertAddressMapping(
                "Auf der Vogelwiese 23 b",
                "{\"street\":\"Auf der Vogelwiese\",\"housenumber\":\"23 b\"}"
        );
    }

    @Test
    public void foreignAddressExamples() throws Exception {
        assertAddressMapping(
                "4, rue de la revolution",
                "{\"street\":\"rue de la revolution\",\"housenumber\":\"4\"}"
        );
        assertAddressMapping(
                "200 Broadway Av",
                "{\"street\":\"Broadway Av\",\"housenumber\":\"200\"}"
        );
        assertAddressMapping(
                "Calle Aduana, 29",
                "{\"street\":\"Calle Aduana\",\"housenumber\":\"29\"}"
        );
        assertAddressMapping(
                "Calle 39 No 1540",
                "{\"street\":\"Calle 39\",\"housenumber\":\"No 1540\"}"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void veryLongBogusString() {
        StringBuilder longString = new StringBuilder();
        for ( int i = 0; i<10000; i++ ) {
            longString.append("anotherTerm ");
        }
        convert(longString.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNonAddress() {
        convert("123 456 789 0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void isEmptyString() {
        convert("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void isNullString() {
        convert(null);
    }

    private void assertAddressMapping(String input, String expected) {
        String actual = convert(input);
        assertEquals(actual, expected);
    }

    private String convert(String input) {
        return Main.convert(input);
    }
}
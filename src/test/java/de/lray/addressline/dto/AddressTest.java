package de.lray.addressline.dto;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class AddressTest {

    static final String STREET = "Street";
    static final String HOUSE_NUMBER = "5";

    @Test
    public void constructorDeliveringGetter() throws Exception {
        // Given / When
        Address result = new Address(STREET, HOUSE_NUMBER);
        // Then
        assertEquals(result.getStreet(),STREET);
        assertEquals(result.getHouseNumber(), HOUSE_NUMBER);
    }

    @Test
    public void toStringWorks() throws Exception {
        // Given
        Address anAddress = new Address(STREET, HOUSE_NUMBER);
        // When
        String result = anAddress.toString();
        // Then
        assertThat("Holds Street",result, containsString(STREET));
        assertThat("Holds house number",result, containsString(HOUSE_NUMBER));
    }

    @Test
    public void isNullSafe() throws Exception {
        // Given / When
        Address result = new Address(null, null);
        // Then
        assertNull("Street", result.getStreet());
        assertNull("House number",result.getHouseNumber());
        assertNotNull("Holds ToString", result.toString());
    }
}
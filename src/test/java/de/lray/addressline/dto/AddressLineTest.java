package de.lray.addressline.dto;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class AddressLineTest {

    static final String STREET = "Street";
    static final String HOUSE_NUMBER = "5";

    @Test
    public void constructorDeliveringGetter() throws Exception {
        // Given / When
        AddressLine result = new AddressLine(STREET, HOUSE_NUMBER);
        // Then
        assertEquals(result.getStreet(),STREET);
        assertEquals(result.getHouseNumber(), HOUSE_NUMBER);
    }

    @Test
    public void toStringWorks() throws Exception {
        // Given
        AddressLine anAddressLine = new AddressLine(STREET, HOUSE_NUMBER);
        // When
        String result = anAddressLine.toString();
        // Then
        assertThat("Holds Street",result, containsString(STREET));
        assertThat("Holds house number",result, containsString(HOUSE_NUMBER));
    }

    @Test
    public void isNullSafe() throws Exception {
        // Given / When
        AddressLine result = new AddressLine(null, null);
        // Then
        assertNull("Street", result.getStreet());
        assertNull("House number",result.getHouseNumber());
        assertNotNull("Holds ToString", result.toString());
    }
}
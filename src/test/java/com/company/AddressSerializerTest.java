package com.company;

import com.company.dto.Address;
import org.junit.Test;

import javax.json.JsonObject;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AddressSerializerTest {
    @Test(expected = IllegalArgumentException.class)
    public void serializesNullValue() {
        AddressSerializer.serialize(null);
    }

    @Test
    public void serializesEmptyValue() {
        final JsonObject result = AddressSerializer.serialize(new Address(null, null));
        assertNotNull(result);
    }

    @Test
    public void serializesHappyPath() {
        // Given
        final String street = "street";
        final String number = "number";
        final Address input = new Address(street, number);
        // When
        final JsonObject result = AddressSerializer.serialize(input);

        // Then
        assertNotNull(result);
        assertThat("Holds street",result.toString(), containsString(street));
        assertThat("Holds number",result.toString(), containsString(number));
    }
}
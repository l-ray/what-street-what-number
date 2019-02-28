package de.lray.addressline;

import de.lray.addressline.dto.Address;
import org.junit.Test;

import javax.json.JsonObject;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SerializerTest {
    @Test(expected = IllegalArgumentException.class)
    public void serializesNullValue() {
        Serializer.serialize(null);
    }

    @Test
    public void serializesEmptyValue() {
        final JsonObject result = Serializer.serialize(new Address(null, null));
        assertNotNull(result);
    }

    @Test
    public void serializesHappyPath() {
        // Given
        final String street = "street";
        final String number = "number";
        final Address input = new Address(street, number);
        // When
        final JsonObject result = Serializer.serialize(input);

        // Then
        assertNotNull(result);
        assertThat("Holds street",result.toString(), containsString(street));
        assertThat("Holds number",result.toString(), containsString(number));
    }
}
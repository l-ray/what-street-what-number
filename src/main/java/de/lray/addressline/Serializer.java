package de.lray.addressline;

import de.lray.addressline.dto.AddressLine;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

abstract class Serializer {

    private static final String STREET_ELEMENT_NAME = "street";
    private static final String HOUSENUMBER_ELEMENT_NAME = "housenumber";

    static JsonObject serialize(AddressLine anAddressLine) {
        if (anAddressLine == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }
        JsonObjectBuilder json = Json.createObjectBuilder();
        if (anAddressLine.getStreet() != null) {
            json.add(STREET_ELEMENT_NAME, anAddressLine.getStreet());
        }
        if (anAddressLine.getHouseNumber() != null) {
            json.add(HOUSENUMBER_ELEMENT_NAME, anAddressLine.getHouseNumber());
        }
        return json.build();
    }
}

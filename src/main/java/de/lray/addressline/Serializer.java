package de.lray.addressline;

import de.lray.addressline.dto.AddressLine;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

abstract class Serializer {
    static JsonObject serialize(AddressLine anAddressLine) {
        if (anAddressLine == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }
        JsonObjectBuilder json = Json.createObjectBuilder();
        if (anAddressLine.getStreet() != null) {
            json.add("street", anAddressLine.getStreet());
        }
        if (anAddressLine.getHouseNumber() != null) {
            json.add("housenumber", anAddressLine.getHouseNumber());
        }
        return json.build();
    }
}

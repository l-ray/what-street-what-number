package de.lray.addressline;

import de.lray.addressline.dto.Address;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public abstract class Serializer {
    public static JsonObject serialize(Address anAddress) {
        if (anAddress == null) {
            throw new IllegalArgumentException("Null value not allowed.");
        }
        JsonObjectBuilder json = Json.createObjectBuilder();
        if (anAddress.getStreet() != null) {
            json.add("street", anAddress.getStreet());
        }
        if (anAddress.getHouseNumber() != null) {
            json.add("housenumber", anAddress.getHouseNumber());
        }
        return json.build();
    }
}

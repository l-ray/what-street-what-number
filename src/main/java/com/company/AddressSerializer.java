package com.company;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by lray on 27.02.19.
 */
public class AddressSerializer {
    public static JsonObject serialize(Address anAddress) {
        // Create Json and print
        JsonObject json = Json.createObjectBuilder()
            .add("street", anAddress.getStreet())
            .add("housenumber", anAddress.getHouseNumber())
            .build();
        return json;
    }
}

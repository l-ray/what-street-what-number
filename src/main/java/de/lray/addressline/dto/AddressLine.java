package de.lray.addressline.dto;

/**
 * Immutable DTO for address data.
 */
public class AddressLine {
    private String street;
    private String houseNumber;

    public AddressLine(String street, String houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }


    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    @Override
    public String toString() {
        return "\"street\": \"" + getStreet() + "\", \"housenumber\": " + getHouseNumber();
    }
}

package com.company.dto;

public class Address {
    private String street;
    private String houseNumber;

    public Address(String street, String houseNumber) {
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

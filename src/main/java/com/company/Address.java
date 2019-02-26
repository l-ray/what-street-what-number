package com.company;

public class Address {
    private String street;
    private String houseNumber;

    public Address(String street, String housenNumber) {
        this.street = street;
        this.houseNumber = housenNumber;
    }


    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }
}

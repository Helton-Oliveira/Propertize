package com.digisphere.propertize.application.property.domain.component;

import java.util.Map;

public class Address {
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;

    public Address(Map<String, String> address) {
        this.street = address.get("street");
        this.number = address.get("number");
        this.complement = address.get("complement");
        this.neighborhood = address.get("neighborhood");
        this.city = address.get("city");
        this.state = address.get("state");
        this.postalCode = address.get("postalCode");
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postalCode;
    }

    public void setPostal_code(String postal_code) {
        this.postalCode = postal_code;
    }
}

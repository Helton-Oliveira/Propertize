package com.digisphere.propertize.application.property.domain;

import com.digisphere.propertize.application.property.domain.component.Address;
import com.digisphere.propertize.application.property.domain.component.PropertyStatus;
import com.digisphere.propertize.application.property.domain.component.PropertyType;

import java.time.LocalDate;
import java.util.UUID;

public class Property {
    private UUID id;
    private UUID ownerId;
    private String description;
    private Address address;
    private PropertyType type;
    private Double size;
    private Integer bedroomCount;
    private Integer bathroomCount;
    private Boolean hasGarage;
    private Integer rentValue;
    private PropertyStatus status;
    private LocalDate constructionDate;

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(Integer bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public Integer getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(Integer bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public Boolean getHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(Boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public Integer getRentValue() {
        return rentValue;
    }

    public void setRentValue(Integer rentValue) {
        this.rentValue = rentValue;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        this.constructionDate = constructionDate;
    }
}

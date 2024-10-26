package com.digisphere.propertize.application.property.domain.propertyBuilder.builder;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.domain.component.Address;
import com.digisphere.propertize.application.property.domain.component.PropertyStatus;
import com.digisphere.propertize.application.property.domain.component.PropertyType;

import java.time.LocalDate;
import java.util.UUID;

public class PropertyBuilder implements IPropertyBuilder {

    private Property property = new Property();

    @Override
    public Property getProperty() {
        var propertyInstance = this.property;
        this.reset();
        return propertyInstance;
    }

    @Override
    public void reset() {
        this.property = new Property();
    }

    @Override
    public void setId(UUID id) {
        this.property.setId(id);
    }

    @Override
    public void setOwnerId(UUID id) {
        this.property.setOwnerId(id);
    }

    @Override
    public void setDescription(String description) {
        this.property.setDescription(description);
    }

    @Override
    public void setAddress(Address address) {
        this.property.setAddress(address);
    }

    @Override
    public void setType(PropertyType type) {
        this.property.setType(type);
    }

    @Override
    public void setSize(Double size) {
        this.property.setSize(size);
    }

    @Override
    public void setBedroomCount(Integer bedroomCount) {
        this.property.setBedroomCount(bedroomCount);
    }

    @Override
    public void setBathroomCount(Integer bathroomCount) {
        this.property.setBathroomCount(bathroomCount);
    }

    @Override
    public void setHasGarage(Boolean hasGarage) {
        this.property.setHasGarage(hasGarage);
    }

    @Override
    public void setRentValue(Double rentValue) {
        this.property.setRentValue(rentValue);
    }

    @Override
    public void setStatus(PropertyStatus status) {
        this.property.setStatus(status);
    }

    @Override
    public void setConstructionDate(LocalDate constructionDate) {
        this.property.setConstructionDate(constructionDate);
    }

    @Override
    public void setMaintenancePending(Boolean pending) {
        this.property.setMaintenancePending(pending);
    }
}

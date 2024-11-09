package com.digisphere.propertize.application.director.bridgePattern.implementations;

import com.digisphere.propertize.application.director.bridgePattern.abstractions.IBridge;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.domain.component.Address;
import com.digisphere.propertize.application.property.domain.component.PropertyStatus;
import com.digisphere.propertize.application.property.domain.component.PropertyType;
import com.digisphere.propertize.application.property.domain.propertyBuilder.builder.IPropertyBuilder;
import com.digisphere.propertize.application.property.domain.propertyBuilder.builder.PropertyBuilder;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class PropertyDirectorImpl implements IBridge {
    private final IPropertyBuilder propertyBuilder = new PropertyBuilder();
    private Property property;

    @Override
    public void build(Map<String, String> data) {
        propertyBuilder.setId(UUID.randomUUID());
        propertyBuilder.setOwnerId(UUID.fromString(data.get("ownerId")));
        propertyBuilder.setDescription(data.get("description"));
        propertyBuilder.setAddress(new Address(data));
        propertyBuilder.setType(PropertyType.fromString(data.get("type")));
        propertyBuilder.setSize(Double.valueOf(data.get("size")));
        propertyBuilder.setBedroomCount(Integer.parseInt(data.get("bedRoomCount")));
        propertyBuilder.setBathroomCount(Integer.parseInt(data.get("bathRoomCount")));
        propertyBuilder.setHasGarage(Boolean.valueOf(data.get("hasGarage")));
        propertyBuilder.setRentValue(Double.valueOf(data.get("rentValue")));
        propertyBuilder.setStatus(PropertyStatus.fromString(data.get("status")));
        propertyBuilder.setConstructionDate(LocalDate.parse(data.get("constructionDate")));
        propertyBuilder.setMaintenancePending(false);
        property = propertyBuilder.getProperty();
    }

    @Override
    public <T> T get() {
        return (T) property;
    }

}
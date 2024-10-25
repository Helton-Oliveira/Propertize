package com.digisphere.propertize.application.property.domain.propertyBuilder.director;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.domain.component.Address;
import com.digisphere.propertize.application.property.domain.component.PropertyStatus;
import com.digisphere.propertize.application.property.domain.component.PropertyType;
import com.digisphere.propertize.application.property.domain.propertyBuilder.builder.IPropertyBuilder;
import com.digisphere.propertize.application.property.domain.propertyBuilder.builder.PropertyBuilder;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class PropertyDirector implements IPropertyDirector {

    private final IPropertyBuilder builder;

    private PropertyDirector(IPropertyBuilder builder) {
        this.builder = builder;
    }

    public static PropertyDirector createDirector() {
        return new PropertyDirector(new PropertyBuilder());
    }

    @Override
    public Property build() {
        return builder.getProperty();
    }

    @Override
    public void createResidentialProperty(String ownerId, String description, Map<String, String> address, String type, Double size, Integer bedRoomCount, Integer bathRoomCount, Boolean hasGarage, Integer rentValue, String status, LocalDate constructionDate) {
        builder.setId(UUID.randomUUID());
        builder.setOwnerId(UUID.fromString(ownerId));
        builder.setDescription(description);
        builder.setAddress(new Address(address));
        builder.setType(PropertyType.fromString(type));
        builder.setSize(size);
        builder.setBedroomCount(bedRoomCount);
        builder.setBathroomCount(bathRoomCount);
        builder.setHasGarage(hasGarage);
        builder.setRentValue(rentValue);
        builder.setStatus(PropertyStatus.fromString(status));
        builder.setConstructionDate(constructionDate);
    }

}

package com.digisphere.propertize.adapter.dtos.property;

import com.digisphere.propertize.application.property.domain.Property;

public record OutputProperty(
        String id,
        String ownerCpf,
        String description,
        String type,
        String size,
        String bedroomCount,
        String bathroomCount,
        String hasGarage,
        String rentValue,
        String constructionDate,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String postalCode
) {
    public OutputProperty(Property property) {
        this(
                property.getId().toString(),
                property.getOwnerCpf(),
                property.getDescription(),
                property.getType().toString(),
                property.getSize().toString(),
                property.getBedroomCount().toString(),
                property.getBathroomCount().toString(),
                property.getHasGarage().toString(),
                property.getRentValue().toString(),
                property.getConstructionDate().toString(),
                property.getAddress().getStreet(),
                property.getAddress().getNumber(),
                property.getAddress().getComplement(),
                property.getAddress().getNeighborhood(),
                property.getAddress().getCity(),
                property.getAddress().getState(),
                property.getAddress().getPostalCode());
    }
}

package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.domain.propertyBuilder.director.PropertyDirector;

import java.time.LocalDate;
import java.util.Map;

public class CreateProperty implements ICreateProperty{

    @Override
    public Property execute(String ownerId, String description, Map<String, String> address, String type, Double size, Integer bedRoomCount, Integer bathRoomCount, Boolean hasGarage, Integer rentValue, String status, LocalDate constructionDate) {
        var director = PropertyDirector.createDirector();
        director.createResidentialProperty(ownerId, description, address, type, size, bedRoomCount, bathRoomCount, hasGarage, rentValue, status, constructionDate);

        return director.build();
    }
}

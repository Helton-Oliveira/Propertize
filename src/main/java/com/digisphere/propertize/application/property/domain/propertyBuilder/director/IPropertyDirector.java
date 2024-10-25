package com.digisphere.propertize.application.property.domain.propertyBuilder.director;

import com.digisphere.propertize.application.property.domain.Property;

import java.time.LocalDate;
import java.util.Map;

public interface IPropertyDirector {
    Property build();
    void createResidentialProperty(String ownerId, String description, Map<String, String> address, String type, Double size, Integer bedRoomCount, Integer bathRoomCount, Boolean hasGarage, Integer rentValue, String status, LocalDate constructionDate);
}

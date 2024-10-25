package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.property.domain.Property;

import java.time.LocalDate;
import java.util.Map;

public interface ICreateProperty {
    Property execute(String ownerId, String description, Map<String, String> address, String type, Double size, Integer bedRoomCount, Integer bathRoomCount, Boolean hasGarage, Integer rentValue, String status, LocalDate constructionDate);
}

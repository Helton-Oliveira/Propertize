package com.digisphere.propertize.application.property.useCase.interfaces;

import com.digisphere.propertize.application.property.domain.Property;

import java.time.LocalDate;
import java.util.Map;

public interface ICreateProperty {
    Property execute(String ownerId, String description, Map<String, String> address, String type, Double size, Integer bedRoomCount, Integer bathRoomCount, Boolean hasGarage, Double rentValue, String status, LocalDate constructionDate);
}

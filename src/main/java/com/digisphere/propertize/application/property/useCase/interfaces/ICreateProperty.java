package com.digisphere.propertize.application.property.useCase.interfaces;

import com.digisphere.propertize.application.property.domain.Property;

import java.time.LocalDate;
import java.util.Map;

public interface ICreateProperty {
    Property execute(Map<String, String> data);
}

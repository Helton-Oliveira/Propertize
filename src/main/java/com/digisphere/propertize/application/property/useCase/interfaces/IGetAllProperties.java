package com.digisphere.propertize.application.property.useCase.interfaces;

import com.digisphere.propertize.application.property.domain.Property;

import java.util.List;

public interface IGetAllProperties {
    List<Property> execute();
}

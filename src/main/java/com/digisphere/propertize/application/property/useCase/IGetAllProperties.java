package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.property.domain.Property;

import java.util.List;

public interface IGetAllProperties {
    List<Property> execute();
}

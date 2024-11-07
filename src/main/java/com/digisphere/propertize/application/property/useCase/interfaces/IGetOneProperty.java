package com.digisphere.propertize.application.property.useCase.interfaces;

import com.digisphere.propertize.application.property.domain.Property;

public interface IGetOneProperty {
    Property execute(String id);
}

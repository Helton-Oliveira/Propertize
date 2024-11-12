package com.digisphere.propertize.adapter.adapterPattern.PropertyAdapter;

import com.digisphere.propertize.adapter.dtos.property.CreatePropertyDto;
import com.digisphere.propertize.adapter.dtos.property.OutputProperty;
import com.digisphere.propertize.adapter.dtos.property.PropertyReferenceDto;

public interface IPropertyAdapter {
    OutputProperty adaptCreatePropertyRequest(CreatePropertyDto dto);
    OutputProperty adaptFindPropertyRequest(PropertyReferenceDto dto);
    String adaptUpdatePropertyRequest(PropertyReferenceDto dto);
}

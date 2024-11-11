package com.digisphere.propertize.application.property.domain.propertyBuilder.builder;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.domain.component.Address;
import com.digisphere.propertize.application.property.domain.component.PropertyStatus;
import com.digisphere.propertize.application.property.domain.component.PropertyType;

import java.time.LocalDate;
import java.util.UUID;

public interface IPropertyBuilder {
    Property getProperty();
    void reset();
    void setId(UUID id);
    void setOwnerCpf(String ownerCpf);
    void setDescription(String description);
    void setAddress(Address address);
    void setType(PropertyType type);
    void setSize(Double size);
    void setBedroomCount(Integer bedroomCount);
    void setBathroomCount(Integer bathroomCount);
    void setHasGarage(Boolean hasGarage);
    void setRentValue(Double rentValue);
    void setStatus(PropertyStatus status);
    void setConstructionDate(LocalDate constructionDate);
    void setMaintenancePending(Boolean pending);
}

package com.digisphere.propertize.adapter.adapterPattern.PropertyAdapter;

import com.digisphere.propertize.adapter.dtos.property.CreatePropertyDto;
import com.digisphere.propertize.adapter.dtos.property.OutputProperty;
import com.digisphere.propertize.adapter.dtos.property.PropertyReferenceDto;
import com.digisphere.propertize.application.property.useCase.interfaces.ICreateProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.IGetOneProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.IUpdateProperty;

import java.util.HashMap;
import java.util.Map;

public class PropertyAdapter implements IPropertyAdapter{
    private final ICreateProperty createProperty;
    private final IGetOneProperty getOneProperty;
    private final IUpdateProperty updateProperty;

    public PropertyAdapter(ICreateProperty createProperty, IGetOneProperty getOneProperty, IUpdateProperty updateProperty) {
        this.createProperty = createProperty;
        this.getOneProperty = getOneProperty;
        this.updateProperty = updateProperty;
    }

    @Override
    public OutputProperty adaptCreatePropertyRequest(CreatePropertyDto dto) {
        Map<String, String> dataToCreate = new HashMap<>();

        dataToCreate.put("ownerCpf", dto.ownerCpf());
        dataToCreate.put("description", dto.description());
        dataToCreate.put("type", dto.type());
        dataToCreate.put("size", dto.size());
        dataToCreate.put("bedroomCount", dto.bedroomCount());
        dataToCreate.put("bathroomCount", dto.bathroomCount());
        dataToCreate.put("hasGarage", dto.hasGarage());
        dataToCreate.put("rentValue", dto.rentValue());
        dataToCreate.put("constructionDate", dto.constructionDate());
        dataToCreate.put("street", dto.street());
        dataToCreate.put("number", dto.number());
        dataToCreate.put("complement", dto.complement() != null ? dto.complement() : "");
        dataToCreate.put("neighborhood", dto.neighborhood());
        dataToCreate.put("city", dto.city());
        dataToCreate.put("state", dto.state());
        dataToCreate.put("postalCode", dto.postalCode());

        var property = createProperty.execute(dataToCreate);
        return new OutputProperty(property);
    }

    @Override
    public OutputProperty adaptFindPropertyRequest(PropertyReferenceDto dto) {
        var property = getOneProperty.execute(dto.id());
        return new OutputProperty(property);
    }

    @Override
    public String adaptUpdatePropertyRequest(PropertyReferenceDto dto) {
        Map<String, String> newData = new HashMap<>();
        if(dto.rentValue() != null) newData.put("rentValue", dto.rentValue());

        return updateProperty.execute(dto.id(), newData);
    }

}

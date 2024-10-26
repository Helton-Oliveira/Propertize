package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.director.Director;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CreateProperty implements ICreateProperty {

    private final IRepositoryContext repositoryContext;

    public CreateProperty(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public Property execute(String ownerId, String description, Map<String, String> address, String type, Double size, Integer bedRoomCount, Integer bathRoomCount, Boolean hasGarage, Double rentValue, String status, LocalDate constructionDate) {
        repositoryContext.changeState("users");
        var owner = (User) repositoryContext.getOne(ownerId);
        if (owner == null || owner.getActive().equals(false) || !owner.getRole().name().equals("OWNER")) throw new RuntimeException("ERRO! PROPRIETÁRIO NÃO EXISTENTE NO SISTEMA."); // validação temporaria

        var director = Director.createPropertyDirector();
        director.createProperty(owner.getId().toString(), description, address, type, size, bedRoomCount, bathRoomCount, hasGarage, rentValue, status, constructionDate);
        var property = director.buildProperty();

        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("property", property);
        repositoryContext.changeState("properties");
        var isSaved = repositoryContext.save(propertyMap);

        if (!isSaved) throw new RuntimeException("ERRO! IMPOSSÍVEL SALVAR PROPRIEDADE");
        return property;
    }
}

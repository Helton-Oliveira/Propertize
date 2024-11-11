package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.useCase.interfaces.ICreateProperty;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.HashMap;
import java.util.Map;

public class CreateProperty implements ICreateProperty {

    private final IRepositoryContext repositoryContext;
    private final ITemplateMethod abstractDirector;

    public CreateProperty(IRepositoryContext repositoryContext, ITemplateMethod abstractDirector) {
        this.repositoryContext = repositoryContext;
        this.abstractDirector = abstractDirector;
    }

    @Override
    public Property execute(Map<String, String> data) {
        repositoryContext.changeState("users");
        User owner = repositoryContext.getOne(data.get("ownerCpf"));
        if (owner == null || owner.getActive().equals(false) || !owner.getRole().name().equals("OWNER")) throw new RuntimeException("ERRO! PROPRIETÁRIO NÃO EXISTENTE NO SISTEMA."); // validação temporaria

        Property property = abstractDirector.build(data);

        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("property", property);
        repositoryContext.changeState("properties");
        var isSaved = repositoryContext.save(propertyMap);

        if (!isSaved) throw new RuntimeException("ERRO! IMPOSSÍVEL SALVAR PROPRIEDADE");
        return property;
    }
}

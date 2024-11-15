package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.property.businessRules.IPropertyRules;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.useCase.interfaces.ICreateProperty;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProperty implements ICreateProperty {

    private final IRepositoryContext repositoryContext;
    private final ITemplateMethod abstractDirector;
    private final List<IPropertyRules> propertyRules;

    public CreateProperty(IRepositoryContext repositoryContext, ITemplateMethod abstractDirector, List<IPropertyRules> propertyRules) {
        this.repositoryContext = repositoryContext;
        this.abstractDirector = abstractDirector;
        this.propertyRules = propertyRules;
    }

    @Override
    public Property execute(Map<String, String> data) {
        Property property = abstractDirector.build(data);
        propertyRules.forEach(r -> r.valid(property));
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("property", property);
        repositoryContext.changeState("properties");
        var isSaved = repositoryContext.save(propertyMap);

        if (!isSaved) throw new RuntimeException("ERRO! IMPOSSÍVEL SALVAR PROPRIEDADE");
        return property;
    }
}

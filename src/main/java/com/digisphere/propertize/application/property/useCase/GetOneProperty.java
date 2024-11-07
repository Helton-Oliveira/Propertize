package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.useCase.interfaces.IGetOneProperty;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public class GetOneProperty implements IGetOneProperty {
   private final IRepositoryContext repositoryContext;

    public GetOneProperty(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public Property execute(String id) {
        repositoryContext.changeState("properties");
        var property = (Property) repositoryContext.getOne(id);
        return property;
    }
}

package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.List;

public class GetAllProperties implements IGetAllProperties{

    private final IRepositoryContext repositoryContext;

    public GetAllProperties(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public List<Property> execute() {
        repositoryContext.changeState("properties");
        return repositoryContext.getAll().stream()
                .map(p -> (Property) p )
                .toList();
    }
}

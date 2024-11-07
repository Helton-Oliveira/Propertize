package com.digisphere.propertize.application.property.useCase;

import com.digisphere.propertize.application.property.useCase.interfaces.IUpdateProperty;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.Map;

public class UpdateProperty implements IUpdateProperty {
    private final IRepositoryContext repositoryContext;

    public UpdateProperty(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public String execute(String id, Map<String, String> newData) {
        repositoryContext.changeState("properties");

        return repositoryContext.update(id, newData);
    }
}

package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.user.useCase.interfaces.IDisableUser;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public class DisableUser implements IDisableUser {
    private final IRepositoryContext repositoryContext;

    public DisableUser(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public String execute(String id) {
        repositoryContext.changeState("users");
        return repositoryContext.delete(id);
    }
}

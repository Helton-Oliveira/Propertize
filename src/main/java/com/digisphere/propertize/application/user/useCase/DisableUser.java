package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.user.businessRules.IUserRules;
import com.digisphere.propertize.application.user.useCase.interfaces.IDisableUser;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.List;

public class DisableUser implements IDisableUser {
    private final IRepositoryContext repositoryContext;
    private final List<IUserRules> userRules;

    public DisableUser(IRepositoryContext repositoryContext, List<IUserRules> userRules) {
        this.repositoryContext = repositoryContext;
        this.userRules = userRules;
    }

    @Override
    public String execute(String id) {
        repositoryContext.changeState("users");
        userRules.forEach(r -> r.valid(repositoryContext));
        return repositoryContext.delete(id);
    }
}

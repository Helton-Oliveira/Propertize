package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.interfaces.IGetOneUSer;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public class GetOneUser implements IGetOneUSer {

    private final IRepositoryContext repositoryContext;

    public GetOneUser(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public User execute(String id) {
        repositoryContext.changeState("users");
        var user = repositoryContext.getOne(id);
        return (User) user;
    }
}

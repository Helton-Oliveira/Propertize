package com.digisphere.propertize.user.useCase;

import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.user.domain.User;

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

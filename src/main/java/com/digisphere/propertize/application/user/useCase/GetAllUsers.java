package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.interfaces.IGetAllUsers;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.List;

public class GetAllUsers implements IGetAllUsers {
    private final IRepositoryContext repositoryContext;

    public GetAllUsers(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public List<User> execute() {
        repositoryContext.changeState("users");
        var userList = repositoryContext.getAll();
        return userList.stream()
                .map(u -> (User) u )
                .toList();
    }
}

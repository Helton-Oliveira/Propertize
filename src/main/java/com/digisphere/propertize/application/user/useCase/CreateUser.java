package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.interfaces.ICreateUser;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.IContext;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.HashMap;
import java.util.Map;

public class CreateUser implements ICreateUser {

    private final IRepositoryContext repositoryContext;
    private final IContext context;

    public CreateUser(IRepositoryContext stateContext, IContext context) {
        this.repositoryContext = stateContext;
        this.context = context;
    }

    @Override
    public User execute(Map<String, String> attributes) {
        context.setStrategy(attributes);
        var user = context.executeStrategy(attributes);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", user);

        repositoryContext.changeState("users");
        var isSaved = repositoryContext.save(userMap);
        if (!isSaved) return null;

        return user;
    }
}

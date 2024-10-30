package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.observerPattern.IEventManager;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.interfaces.ICreateUser;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.IContext;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.HashMap;
import java.util.Map;

public class CreateUser implements ICreateUser {

    private final IRepositoryContext repositoryContext;
    private final IContext context;
    private final IEventManager eventManager;


    public CreateUser(IRepositoryContext stateContext, IContext context, IEventManager eventManager) {
        this.repositoryContext = stateContext;
        this.context = context;
        this.eventManager = eventManager;
    }

    @Override
    public User execute(Map<String, String> attributes) {
        context.setStrategy(attributes);
        var user = context.executeStrategy(attributes);

        Map<String, String> dataForNotify = new HashMap<>();
        dataForNotify.put("email", user.getEmail());
        dataForNotify.put("userName", user.getName());
        dataForNotify.put("password", user.getPassword());
        eventManager.subscribe("emailAlert");
        eventManager.notifySubscribe(dataForNotify);

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", user);

        repositoryContext.changeState("users");
        var isSaved = repositoryContext.save(userMap);
        if (!isSaved) return null;

        return user;
    }
}

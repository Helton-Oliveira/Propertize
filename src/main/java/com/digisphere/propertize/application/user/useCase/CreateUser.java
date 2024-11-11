package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.observerPattern.subject.IEventManager;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.interfaces.ICreateUser;
import com.digisphere.propertize.application.user.utils.PasswordEncryptUtil;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.HashMap;
import java.util.Map;

public class CreateUser implements ICreateUser {

    private final IRepositoryContext repositoryContext;
    private final IEventManager eventManager;
    private final ITemplateMethod abstractDirector;

    public CreateUser(IRepositoryContext stateContext, IEventManager eventManager, ITemplateMethod abstractDirector) {
        this.repositoryContext = stateContext;
        this.eventManager = eventManager;
        this.abstractDirector = abstractDirector;
    }

    @Override
    public User execute(Map<String, String> attributes) {

        User user = abstractDirector.build(attributes);

        Map<String, String> dataForNotify = new HashMap<>();
        dataForNotify.put("email", user.getEmail());
        dataForNotify.put("userName", user.getName());
        dataForNotify.put("password", user.getPassword());
        eventManager.subscribe("emailAlert");
        eventManager.notifyAll(dataForNotify);
        user.setPassword(PasswordEncryptUtil.execute(user.getPassword()));

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("user", user);

        repositoryContext.changeState("users");
        var isSaved = repositoryContext.save(userMap);
        if (!isSaved) return null;

        return user;
    }
}

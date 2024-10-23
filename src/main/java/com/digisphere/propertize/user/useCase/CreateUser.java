package com.digisphere.propertize.user.useCase;

import com.digisphere.propertize.user.domain.User;
import com.digisphere.propertize.user.useCase.strategyPattern.context.IContext;

import java.util.Map;

public class CreateUser implements ICreateUser {

    private final IContext context;

    public CreateUser(IContext context) {
        this.context = context;
    }

    @Override
    public User execute(Map<String, String> attributes) {
        context.setStrategy(attributes);

        return context.executeStrategy(attributes);
    }
}

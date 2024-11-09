package com.digisphere.propertize.application.director.bridgePattern.implementations;

import com.digisphere.propertize.application.director.bridgePattern.abstractions.IBridge;
import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.builderPattern.builder.UserBuilder;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.Context;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.IContext;

import java.util.Map;

public class UserDirectorImpl implements IBridge {
    private final IUserBuilder userBuilder = new UserBuilder();
    private final IContext context = new Context();
    private User user;

    @Override
    public void build(Map<String, String> data) {
        context.setStrategy(data);
        this.user = context.executeStrategy(data, userBuilder);
    }

    @Override
    public <T> T get() {
        return (T) user;
    }
}

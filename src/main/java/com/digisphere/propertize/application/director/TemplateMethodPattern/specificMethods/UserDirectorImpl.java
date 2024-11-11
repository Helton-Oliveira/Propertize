package com.digisphere.propertize.application.director.TemplateMethodPattern.specificMethods;

import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.builderPattern.builder.UserBuilder;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.Context;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.IContext;

import java.util.Map;

public class UserDirectorImpl implements IDirector {
    private final IUserBuilder userBuilder = new UserBuilder();
    private final IContext context = new Context();
    private User user;

    @Override
    public void buildEntity(Map<String, String> data) {
        context.setStrategy(data);
        user = context.executeStrategy(data, userBuilder);
    }

    @Override
    public <T> T get() {
        return (T) user;
    }
}

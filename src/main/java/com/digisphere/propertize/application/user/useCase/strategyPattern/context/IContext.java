package com.digisphere.propertize.application.user.useCase.strategyPattern.context;

import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.domain.User;

import java.util.Map;

public interface IContext {
    void setStrategy(Map<String, String> data);
    User executeStrategy(Map<String, String> data, IUserBuilder userBuilder);
}

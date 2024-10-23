package com.digisphere.propertize.user.useCase.strategyPattern.context;

import com.digisphere.propertize.user.domain.User;

import java.util.Map;

public interface IContext {
    void setStrategy(Map<String, String> data);
    User executeStrategy(Map<String, String> data);
}

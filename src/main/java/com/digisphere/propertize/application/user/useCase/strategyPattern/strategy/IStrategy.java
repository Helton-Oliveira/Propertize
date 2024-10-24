package com.digisphere.propertize.application.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.application.user.domain.User;

import java.util.Map;

public interface IStrategy {
    User execute(Map<String, String> data);
}

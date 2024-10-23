package com.digisphere.propertize.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.user.domain.User;

import java.util.Map;

public interface IStrategy {
    User execute(Map<String, String> data);
}

package com.digisphere.propertize.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.user.builderPattern.director.Director;
import com.digisphere.propertize.user.domain.User;

import java.util.Map;

public class CreateTenant implements IStrategy{
    @Override
    public User execute(Map<String, String> data) {
        var director = Director.createDirector();
        director.createTenant(data.get("name"), data.get("cpf"), data.get("email"), data.get("phone"));

        return director.build();
    }
}

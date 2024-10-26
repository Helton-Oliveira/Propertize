package com.digisphere.propertize.application.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.director.Director;

import java.util.Map;

public class CreateTenant implements IStrategy{
    @Override
    public User execute(Map<String, String> data) {
        var director = Director.createUserDirector();
        director.createTenant(data.get("name"), data.get("cpf"), data.get("email"), data.get("phone"));

        return director.buildUser();
    }
}

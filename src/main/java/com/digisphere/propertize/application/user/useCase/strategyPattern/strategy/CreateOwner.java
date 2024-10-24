package com.digisphere.propertize.application.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.application.user.builderPattern.director.Director;
import com.digisphere.propertize.application.user.domain.User;

import java.util.Map;

public class CreateOwner implements IStrategy{
    @Override
    public User execute(Map<String, String> data) {
        var director = Director.createDirector();
        director.createOwner(data.get("name"), data.get("cpf"), data.get("email"), data.get("phone"));

        return director.build();
    }
}

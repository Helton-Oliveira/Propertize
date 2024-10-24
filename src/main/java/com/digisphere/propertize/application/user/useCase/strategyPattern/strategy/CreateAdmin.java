package com.digisphere.propertize.application.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.builderPattern.director.Director;

import java.util.Map;

public class CreateAdmin implements IStrategy {

    @Override
    public User execute(Map<String, String> data) {
        var admin = Director.createDirector();
        admin.createAdmin(data.get("name"), data.get("cpf"), data.get("phone"));

        return admin.build();
    }
}

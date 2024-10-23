package com.digisphere.propertize.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.user.builderPattern.director.Director;
import com.digisphere.propertize.user.domain.User;

import java.util.Map;

public class CreateAdmin implements IStrategy {

    @Override
    public User execute(Map<String, String> data) {
        var admin = Director.createDirector();
        admin.createAdmin(data.get("name"), data.get("cpf"), data.get("phone"));

        return admin.build();
    }
}

package com.digisphere.propertize.user.useCase;

import com.digisphere.propertize.user.builderPattern.director.Director;
import com.digisphere.propertize.user.domain.User;

import java.util.Map;

public class CreateUser implements ICreateUser {

    @Override
    public User execute(Map<String, String> attributes) {
        var user = Director.createDirector();
        user.createUser(attributes.get("name"), attributes.get("email"), attributes.get("cpf"), attributes.get("password"), attributes.get("phone"), attributes.get("role"));
        return user.build();
    }
}

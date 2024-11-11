package com.digisphere.propertize.application.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.domain.Role;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.utils.RandomPasswordGenerator;

import java.util.Map;
import java.util.UUID;

public class CreateOwner implements IStrategy{

    @Override
    public User execute(Map<String, String> data, IUserBuilder userBuilder) {
        userBuilder.setName(data.get("name"));
        userBuilder.setEmail(data.get("email"));
        userBuilder.setCpf(data.get("cpf"));
        userBuilder.setPassword(RandomPasswordGenerator.toGenerate());
        userBuilder.setPhone(data.get("phone"));
        userBuilder.setActive(true);
        userBuilder.setRole(Role.OWNER);

        return userBuilder.getUser();
    }
}

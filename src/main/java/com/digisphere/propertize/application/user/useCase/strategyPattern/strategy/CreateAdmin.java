package com.digisphere.propertize.application.user.useCase.strategyPattern.strategy;

import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.domain.Role;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.utils.CorporateEmailCreator;
import com.digisphere.propertize.application.user.utils.RandomPasswordGenerator;

import java.util.Map;
import java.util.UUID;

public class CreateAdmin implements IStrategy {

    @Override
    public User execute(Map<String, String> data, IUserBuilder userBuilder) {
        userBuilder.setId(UUID.randomUUID());
        userBuilder.setName(data.get("name"));
        userBuilder.setEmail(CorporateEmailCreator.create(data.get("name")));
        userBuilder.setCpf(data.get("cpf"));
        userBuilder.setPassword(RandomPasswordGenerator.toGenerate());
        userBuilder.setPhone(data.get("phone"));
        userBuilder.setActive(true);
        userBuilder.setRole(Role.ADMIN);

        return userBuilder.getUser();
    }
}

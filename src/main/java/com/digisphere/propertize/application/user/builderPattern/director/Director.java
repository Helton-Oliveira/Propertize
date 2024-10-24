package com.digisphere.propertize.application.user.builderPattern.director;

import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.builderPattern.builder.UserBuilder;
import com.digisphere.propertize.application.user.domain.Role;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.utils.CorporateEmailCreator;
import com.digisphere.propertize.application.user.utils.RandomPasswordGenerator;

import java.util.UUID;

public class Director implements IDirector {

    private final IUserBuilder builder;

    private Director(IUserBuilder builder) {
        this.builder = builder;
    }

    public static Director createDirector() {
        var builder = new UserBuilder();
        return new Director(builder);
    }

    @Override
    public void createAdmin(String name, String cpf, String phone) {
        builder.setId(UUID.randomUUID());
        builder.setName(name);
        builder.setEmail(CorporateEmailCreator.create(name));
        builder.setCpf(cpf);
        builder.setPassword(RandomPasswordGenerator.toGenerate());
        builder.setPhone(phone);
        builder.setActive(true);
        builder.setRole(Role.ADMIN);
    }

    @Override
    public void createTenant(String name, String cpf, String email, String phone) {
        builder.setId(UUID.randomUUID());
        builder.setName(name);
        builder.setEmail(email);
        builder.setCpf(cpf);
        builder.setPassword(RandomPasswordGenerator.toGenerate());
        builder.setPhone(phone);
        builder.setActive(true);
        builder.setRole(Role.TENANT);
    }

    @Override
    public void createOwner(String name, String cpf, String email, String phone) {
        builder.setId(UUID.randomUUID());
        builder.setName(name);
        builder.setEmail(email);
        builder.setCpf(cpf);
        builder.setPassword(RandomPasswordGenerator.toGenerate());
        builder.setPhone(phone);
        builder.setActive(true);
        builder.setRole(Role.OWNER);
    }

    @Override
    public User build() {
        return builder.getUser();
    }

}

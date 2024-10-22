package com.digisphere.propertize.user.builderPattern.director;

import com.digisphere.propertize.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.user.builderPattern.builder.UserBuilder;
import com.digisphere.propertize.user.domain.Role;
import com.digisphere.propertize.user.domain.User;

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
    public void createUser(String name, String email, String cpf, String password, String phone, String role) {
        builder.setId(UUID.randomUUID());
        builder.setName(name);
        builder.setEmail(email);
        builder.setCpf(cpf);
        builder.setPassword(password);
        builder.setPhone(phone);
        builder.setActive(true);
        builder.setRole(Role.valueOf(role.toUpperCase()));
    }

    @Override
    public User build() {
        return builder.getUser();
    }
}

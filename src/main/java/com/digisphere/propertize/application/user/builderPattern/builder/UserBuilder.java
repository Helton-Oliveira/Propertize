package com.digisphere.propertize.application.user.builderPattern.builder;

import com.digisphere.propertize.application.user.domain.Role;
import com.digisphere.propertize.application.user.domain.User;

import java.util.UUID;

public class UserBuilder implements IUserBuilder {

    private User user = new User();

    @Override
    public User getUser() {
        var userInstance = this.user;
        this.reset();
        return userInstance;
    }

    @Override
    public void reset() {
        this.user = new User();
    }

    @Override
    public void setId(UUID id) {
        this.user.setId(id);
    }

    @Override
    public void setName(String name) {
        this.user.setName(name);
    }

    @Override
    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    @Override
    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    @Override
    public void setCpf(String cpf) {
        this.user.setCpf(cpf);
    }

    @Override
    public void setPhone(String phone) {
        this.user.setPhone(phone);
    }

    @Override
    public void setActive(Boolean active) {
        this.user.setActive(active);
    }

    @Override
    public void setRole(Role role) {
        this.user.setRole(role);
    }
}

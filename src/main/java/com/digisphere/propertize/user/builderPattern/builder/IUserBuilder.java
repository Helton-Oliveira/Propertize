package com.digisphere.propertize.user.builderPattern.builder;

import com.digisphere.propertize.user.domain.Role;
import com.digisphere.propertize.user.domain.User;

import java.util.UUID;

public interface IUserBuilder {
    User getUser();
    void reset();
    void setId(UUID id);
    void setName(String name);
    void setEmail(String email);
    void setPassword(String password);
    void setCpf(String cpf);
    void setPhone(String phone);
    void setActive(Boolean active);
    void setRole(Role role);
}

package com.digisphere.propertize.user.builderPattern.director;

import com.digisphere.propertize.user.domain.User;

public interface IDirector {
    void createAdmin(String name, String cpf, String phone);
    void createTenant(String name, String cpf, String email, String phone);
    void createOwner(String name, String cpf, String email, String phone);
    User build();
}

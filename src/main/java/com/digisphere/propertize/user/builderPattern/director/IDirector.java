package com.digisphere.propertize.user.builderPattern.director;

import com.digisphere.propertize.user.domain.User;

public interface IDirector {
    void createUser(String name, String email, String cpf, String password, String phone, String role);
    User build();
}

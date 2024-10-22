package com.digisphere.propertize.user.useCase;

import com.digisphere.propertize.user.domain.User;

import java.util.Map;

public interface ICreateUser {
    User execute(Map<String, String> stringMap);
}

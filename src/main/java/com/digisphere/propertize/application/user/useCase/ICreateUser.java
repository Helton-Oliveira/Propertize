package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.user.domain.User;

import java.util.Map;

public interface ICreateUser {
    User execute(Map<String, String> stringMap);
}

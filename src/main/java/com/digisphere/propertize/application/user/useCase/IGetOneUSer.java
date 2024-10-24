package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.user.domain.User;

public interface IGetOneUSer {
    User execute(String id);
}

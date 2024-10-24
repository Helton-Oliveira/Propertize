package com.digisphere.propertize.user.useCase;

import com.digisphere.propertize.user.domain.User;

public interface IGetOneUSer {
    User execute(String id);
}

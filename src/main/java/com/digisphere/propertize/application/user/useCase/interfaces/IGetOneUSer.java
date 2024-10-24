package com.digisphere.propertize.application.user.useCase.interfaces;

import com.digisphere.propertize.application.user.domain.User;

public interface IGetOneUSer {
    User execute(String id);
}

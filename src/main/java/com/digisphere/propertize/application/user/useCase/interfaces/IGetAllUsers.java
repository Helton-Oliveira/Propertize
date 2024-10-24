package com.digisphere.propertize.application.user.useCase.interfaces;

import com.digisphere.propertize.application.user.domain.User;

import java.util.List;

public interface IGetAllUsers {
    List<User> execute();
}

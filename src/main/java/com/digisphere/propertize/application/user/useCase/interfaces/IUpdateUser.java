package com.digisphere.propertize.application.user.useCase.interfaces;

import java.util.Map;

public interface IUpdateUser {
    String execute(String id, Map<String, String> data);
}

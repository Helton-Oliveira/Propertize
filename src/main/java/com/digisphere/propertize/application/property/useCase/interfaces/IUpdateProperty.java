package com.digisphere.propertize.application.property.useCase.interfaces;

import java.util.Map;

public interface IUpdateProperty {
    String execute(String id, Map<String, String> newData);
}

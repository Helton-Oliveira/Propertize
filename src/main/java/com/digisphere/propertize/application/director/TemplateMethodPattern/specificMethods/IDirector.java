package com.digisphere.propertize.application.director.TemplateMethodPattern.specificMethods;

import java.util.Map;

public interface IDirector {
    void buildEntity(Map<String, String> data);
    <T> T get();
}

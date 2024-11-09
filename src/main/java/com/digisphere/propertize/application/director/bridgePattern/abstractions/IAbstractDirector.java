package com.digisphere.propertize.application.director.bridgePattern.abstractions;

import java.util.Map;

public interface IAbstractDirector {
    <T> T build(Map<String, String> data);
}

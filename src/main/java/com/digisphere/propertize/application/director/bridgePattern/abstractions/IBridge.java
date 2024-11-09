package com.digisphere.propertize.application.director.bridgePattern.abstractions;

import java.util.Map;

public interface IBridge {
    void build(Map<String, String> data);
    <T> T get();
}

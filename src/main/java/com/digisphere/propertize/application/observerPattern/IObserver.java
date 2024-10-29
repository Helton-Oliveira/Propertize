package com.digisphere.propertize.application.observerPattern;

import java.util.Map;

public interface IObserver {
    void update(Map<String, String> data);
}

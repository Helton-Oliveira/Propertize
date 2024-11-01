package com.digisphere.propertize.application.observerPattern.observers;

import java.util.Map;

public interface IObserver {
    void update(Map<String, String> data);
}

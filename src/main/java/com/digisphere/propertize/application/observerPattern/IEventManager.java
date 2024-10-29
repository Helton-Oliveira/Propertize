package com.digisphere.propertize.application.observerPattern;

import java.util.Map;

public interface IEventManager {
    void subscribe(String eventType);
    void unsubscribe(IObserver listener);
    void notifySubscribers(Map<String, String> data);
}

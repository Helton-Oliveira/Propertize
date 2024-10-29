package com.digisphere.propertize.application.observerPattern;

import java.util.Map;

public interface IEventManager {
    void subscribe(String eventType);
    void unsubscribe(IObserver listener);
    void notifySubscribe(Map<String, String> data);
    void notifyAll(Map<String, String> data);
}

package com.digisphere.propertize.application.observerPattern.subject;

import com.digisphere.propertize.application.observerPattern.observers.IObserver;

import java.util.Map;

public interface IEventManager {
    void subscribe(String eventType);
    void unsubscribe(IObserver listener);
    void notifySubscribe(Map<String, String> data);
    void notifyAll(Map<String, String> data);
}

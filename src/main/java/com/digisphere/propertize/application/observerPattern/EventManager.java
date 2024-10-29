package com.digisphere.propertize.application.observerPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventManager implements IEventManager {

    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void subscribe(String eventType) {
        if (eventType.equalsIgnoreCase("emailAlert")) observers.add(new EmailAlertListener());
    }

    @Override
    public void unsubscribe(IObserver listener) {
        observers.remove(listener);
    }

    @Override
    public void notifySubscribers(Map<String, String> data) {
        observers.forEach(l -> l.update(data));
    }

}

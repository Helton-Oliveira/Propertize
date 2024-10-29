package com.digisphere.propertize.application.observerPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventManager implements IEventManager {

    private List<IObserver> observers = new ArrayList<>();
    private IObserver observer;

    @Override
    public void subscribe(String eventType) {
        if (eventType.equalsIgnoreCase("EMAILALERT")) this.observer = new EmailAlertListener();
        observers.add(this.observer);
    }

    @Override
    public void unsubscribe(IObserver listener) {
        observers.removeIf(l -> l.equals(listener));
    }

    @Override
    public void notifySubscribe(Map<String, String> data) {
        observer.update(data);
    }

    @Override
    public void notifyAll(Map<String, String> data) {
        observers.forEach(l -> l.update(data));
    }

}

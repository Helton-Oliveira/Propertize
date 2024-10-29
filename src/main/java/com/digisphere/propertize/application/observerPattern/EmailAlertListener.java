package com.digisphere.propertize.application.observerPattern;

import java.util.Map;

public class EmailAlertListener implements IObserver {

    @Override
    public void update(Map<String, String> data) {
        System.out.println(data);
    }
}

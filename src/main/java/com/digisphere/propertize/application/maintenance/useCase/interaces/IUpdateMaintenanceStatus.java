package com.digisphere.propertize.application.maintenance.useCase.interaces;

import java.util.Map;

public interface IUpdateMaintenanceStatus {
    String execute(String protocol, Map<String, String> data);
}

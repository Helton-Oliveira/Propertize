package com.digisphere.propertize.application.maintenance.useCase.interaces;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;

import java.util.Map;

public interface IOpenProtocol {
    MaintenanceProtocol execute(Map<String, String> data);
}

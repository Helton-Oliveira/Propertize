package com.digisphere.propertize.application.maintenance.useCase.interaces;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;

public interface IGetOneMaintenanceProtocol {
    MaintenanceProtocol execute(String protocol);
}

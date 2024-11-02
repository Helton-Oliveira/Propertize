package com.digisphere.propertize.application.maintenance.useCase.interaces;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;

import java.util.List;

public interface IPickUpOpenMaintenanceProtocols {
    List<MaintenanceProtocol> execute();
}

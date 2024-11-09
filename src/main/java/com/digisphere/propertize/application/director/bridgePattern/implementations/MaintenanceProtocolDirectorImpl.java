package com.digisphere.propertize.application.director.bridgePattern.implementations;

import com.digisphere.propertize.application.director.bridgePattern.abstractions.IBridge;
import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.domain.component.MaintenanceStatus;
import com.digisphere.propertize.application.maintenance.maintenanceBuilder.IMaintenanceBuilder;
import com.digisphere.propertize.application.maintenance.maintenanceBuilder.MaintenanceBuilder;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class MaintenanceProtocolDirectorImpl implements IBridge {
    private final IMaintenanceBuilder maintenanceBuilder = new MaintenanceBuilder();
    private MaintenanceProtocol maintenanceProtocol;

    @Override
    public void build(Map<String, String> data) {
        maintenanceBuilder.setProtocol(UUID.randomUUID());
        maintenanceBuilder.setPropertyId(UUID.fromString(data.get("propertyId")));
        maintenanceBuilder.setRequestingTenantId(UUID.fromString(data.get("tenantId")));
        maintenanceBuilder.setOpeningDate(LocalDate.now());
        maintenanceBuilder.setMaintenanceDetails(data.get("MaintenanceDetails"));
        maintenanceBuilder.setMaintenanceStatus(MaintenanceStatus.OPEN);
        maintenanceProtocol = maintenanceBuilder.build();
    }

    @Override
    public <T> T get() {
        return (T) maintenanceProtocol;
    }
}

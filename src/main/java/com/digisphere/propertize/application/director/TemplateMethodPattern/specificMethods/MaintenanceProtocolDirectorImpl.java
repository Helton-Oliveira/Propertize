package com.digisphere.propertize.application.director.TemplateMethodPattern.specificMethods;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.domain.component.MaintenanceStatus;
import com.digisphere.propertize.application.maintenance.maintenanceBuilder.IMaintenanceBuilder;
import com.digisphere.propertize.application.maintenance.maintenanceBuilder.MaintenanceBuilder;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class MaintenanceProtocolDirectorImpl implements IDirector {
    private final IMaintenanceBuilder maintenanceBuilder = new MaintenanceBuilder();
    private MaintenanceProtocol maintenanceProtocol;

    @Override
    public void buildEntity(Map<String, String> data) {
        maintenanceBuilder.setProtocol(UUID.randomUUID());
        maintenanceBuilder.setPropertyId(UUID.fromString(data.get("propertyId")));
        maintenanceBuilder.setRequestingTenantCpf(data.get("tenantCpf"));
        maintenanceBuilder.setOpeningDate(LocalDate.now());
        maintenanceBuilder.setMaintenanceDetails(data.get("maintenanceDetails"));
        maintenanceBuilder.setMaintenanceStatus(MaintenanceStatus.OPEN);
        maintenanceProtocol = maintenanceBuilder.build();
    }

    @Override
    public <T> T get() {
        return (T) maintenanceProtocol;
    }
}

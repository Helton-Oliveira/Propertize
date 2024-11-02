package com.digisphere.propertize.application.maintenance.maintenanceBuilder;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.domain.component.MaintenanceStatus;

import java.time.LocalDate;
import java.util.UUID;

public class MaintenanceBuilder implements IMaintenanceBuilder{

    private MaintenanceProtocol maintenanceProtocol = new MaintenanceProtocol();

    @Override
    public MaintenanceProtocol build() {
        var maintenanceInstance = this.maintenanceProtocol;
        this.reset();
        return maintenanceInstance;
    }

    @Override
    public void reset() {
        this.maintenanceProtocol = new MaintenanceProtocol();
    }

    @Override
    public void setProtocol(UUID protocol) {
        this.maintenanceProtocol.setProtocol(protocol);
    }

    @Override
    public void setPropertyId(UUID propertyId) {
        this.maintenanceProtocol.setPropertyIdForMaintenance(propertyId);
    }

    @Override
    public void setRequestingTenantId(UUID requestingTenantId) {
        this.maintenanceProtocol.setRequestingTenantId(requestingTenantId);
    }

    @Override
    public void setOpeningDate(LocalDate openingDate) {
        this.maintenanceProtocol.setOpeningDate(openingDate);
    }

    @Override
    public void setDateOfResolution(LocalDate dateOfResolution) {
        this.maintenanceProtocol.setDateOfResolution(dateOfResolution);
    }

    @Override
    public void setMaintenanceStatus(MaintenanceStatus status) {
        this.maintenanceProtocol.setStatus(status);
    }

    @Override
    public void setMaintenanceDetails(String details) {
        this.maintenanceProtocol.setMaintenanceDetails(details);
    }

    @Override
    public void setReasonForCancellation(String reason) {
        this.maintenanceProtocol.setReasonForCancellation(reason);
    }
}

package com.digisphere.propertize.application.maintenance.maintenanceBuilder;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.domain.component.MaintenanceStatus;

import java.time.LocalDate;
import java.util.UUID;

public interface IMaintenanceBuilder {
    MaintenanceProtocol build();
    void reset();
    void setProtocol(UUID protocol);
    void setPropertyId(UUID propertyId);
    void setRequestingTenantId(UUID requestingTenantId);
    void setOpeningDate(LocalDate openingDate);
    void setDateOfResolution(LocalDate dateOfResolution);
    void setMaintenanceStatus(MaintenanceStatus status);
    void setMaintenanceDetails(String details);
    void setReasonForCancellation(String reason);

}

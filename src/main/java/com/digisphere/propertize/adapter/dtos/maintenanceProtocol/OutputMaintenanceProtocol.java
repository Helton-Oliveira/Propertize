package com.digisphere.propertize.adapter.dtos.maintenanceProtocol;

public record OutputMaintenanceProtocol(
        String protocol,
        String requestingTenantCpf,
        String propertyIdForMaintenance,
        String openingDate,
        String maintenanceDetails,
        String maintenanceStatus,
        String dateOfResolution,
        String reasonForCancellation
) {
}

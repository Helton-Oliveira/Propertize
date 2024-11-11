package com.digisphere.propertize.application.maintenance.domain;

import com.digisphere.propertize.application.maintenance.domain.component.MaintenanceStatus;

import java.time.LocalDate;
import java.util.UUID;

public class MaintenanceProtocol {
    private UUID protocol;
    private String requestingTenantCpf;
    private UUID propertyIdForMaintenance;
    private LocalDate openingDate;
    private LocalDate dateOfResolution;
    private String maintenanceDetails;
    private MaintenanceStatus status;
    private String reasonForCancellation;

    public String getReasonForCancellation() {
        return reasonForCancellation;
    }

    public void setReasonForCancellation(String reasonForCancellation) {
        this.reasonForCancellation = reasonForCancellation;
    }

    public UUID getProtocol() {
        return protocol;
    }

    public void setProtocol(UUID protocol) {
        this.protocol = protocol;
    }

    public String getRequestingTenantCpf() {
        return requestingTenantCpf;
    }

    public void setRequestingTenantCpf(String requestingTenantCpf) {
        this.requestingTenantCpf = requestingTenantCpf;
    }

    public UUID getPropertyIdForMaintenance() {
        return propertyIdForMaintenance;
    }

    public void setPropertyIdForMaintenance(UUID propertyIdForMaintenance) {
        this.propertyIdForMaintenance = propertyIdForMaintenance;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getDateOfResolution() {
        return dateOfResolution;
    }

    public void setDateOfResolution(LocalDate dateOfResolution) {
        this.dateOfResolution = dateOfResolution;
    }

    public MaintenanceStatus getStatus() {
        return status;
    }

    public void setStatus(MaintenanceStatus status) {
        this.status = status;
    }

    public String getMaintenanceDetails() {
        return maintenanceDetails;
    }

    public void setMaintenanceDetails(String maintenanceDetails) {
        this.maintenanceDetails = maintenanceDetails;
    }
}

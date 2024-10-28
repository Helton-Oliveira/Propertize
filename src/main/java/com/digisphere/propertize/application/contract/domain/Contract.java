package com.digisphere.propertize.application.contract.domain;

import com.digisphere.propertize.application.contract.domain.component.ContractStatus;

import java.time.LocalDate;
import java.util.UUID;

public class Contract {
    private UUID id;
    private UUID propertyId;
    private UUID tenantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double monthlyRent;
    private Integer paymentDueDay;
    private Double securityDeposit;
    private ContractStatus status;
    private Double terminationFee;
    private Boolean isRenewable;
    private Boolean autoRenewal;
    private String maintenanceClause;
    private Boolean subleasingAllowed;
    private String contractTerms;
    private LocalDate terminationDate;
    private String terminationReason;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public Integer getPaymentDueDay() {
        return paymentDueDay;
    }

    public void setPaymentDueDay(Integer paymentDueDay) {
        this.paymentDueDay = paymentDueDay;
    }

    public Double getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(Double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public Double getTerminationFee() {
        return terminationFee;
    }

    public void setTerminationFee(Double terminationFee) {
        this.terminationFee = terminationFee;
    }

    public Boolean getRenewable() {
        return isRenewable;
    }

    public void setRenewable(Boolean renewable) {
        isRenewable = renewable;
    }

    public Boolean getAutoRenewal() {
        return autoRenewal;
    }

    public void setAutoRenewal(Boolean autoRenewal) {
        this.autoRenewal = autoRenewal;
    }

    public String getMaintenanceClause() {
        return maintenanceClause;
    }

    public void setMaintenanceClause(String maintenanceClause) {
        this.maintenanceClause = maintenanceClause;
    }

    public Boolean getSubleasingAllowed() {
        return subleasingAllowed;
    }

    public void setSubleasingAllowed(Boolean subleasingAllowed) {
        this.subleasingAllowed = subleasingAllowed;
    }

    public String getContractTerms() {
        return contractTerms;
    }

    public void setContractTerms(String contractTerms) {
        this.contractTerms = contractTerms;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getTerminationReason() {
        return terminationReason;
    }

    public void setTerminationReason(String terminationReason) {
        this.terminationReason = terminationReason;
    }
}

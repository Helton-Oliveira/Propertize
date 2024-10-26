package com.digisphere.propertize.application.contract.contractBuilder;

import com.digisphere.propertize.application.contract.Contract;
import com.digisphere.propertize.application.contract.component.ContractStatus;

import java.time.LocalDate;
import java.util.UUID;

public class ContractBuilder implements IContractBuilder {

    private Contract contract = new Contract();

    @Override
    public Contract build() {
        var contractInstance = this.contract;
        this.reset();
        return contractInstance;
    }

    @Override
    public void reset() {
        this.contract = new Contract();
    }

    @Override
    public void setId(UUID id) {
        this.contract.setId(id);
    }

    @Override
    public void setPropertyId(UUID propertyId) {
        this.contract.setPropertyId(propertyId);
    }

    @Override
    public void setTenantId(UUID tenantId) {
        this.contract.setTenantId(tenantId);
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.contract.setStartDate(startDate);
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.contract.setEndDate(endDate);
    }

    @Override
    public void setMonthlyRent(Double monthlyRent) {
        this.contract.setMonthlyRent(monthlyRent);
    }

    @Override
    public void setPaymentDueDay(Integer paymentDueDay) {
        this.contract.setPaymentDueDay(paymentDueDay);
    }

    @Override
    public void setSecurityDeposit(Double securityDeposit) {
        this.contract.setSecurityDeposit(securityDeposit);
    }

    @Override
    public void setStatus(ContractStatus status) {
        this.contract.setStatus(status);
    }

    @Override
    public void setTerminationFee(Double terminationFee) {
        this.contract.setTerminationFee(terminationFee);
    }

    @Override
    public void setRenewable(Boolean isRenewable) {
        this.contract.setRenewable(isRenewable);
    }

    @Override
    public void setAutoRenewal(Boolean autoRenewal) {
        this.contract.setAutoRenewal(autoRenewal);
    }

    @Override
    public void setMaintenanceClause(String maintenanceClause) {
        this.contract.setMaintenanceClause(maintenanceClause);
    }

    @Override
    public void setSubleasingAllowed(Boolean subleasingAllowed) {
        this.contract.setSubleasingAllowed(subleasingAllowed);
    }

    @Override
    public void setContractTerms(String contractTerms) {
        this.contract.setContractTerms(contractTerms);
    }

    @Override
    public void setTerminationDate(LocalDate terminationDate) {
        this.contract.setTerminationDate(terminationDate);
    }

    @Override
    public void setTerminationReason(String terminationReason) {
        this.contract.setTerminationReason(terminationReason);
    }
}

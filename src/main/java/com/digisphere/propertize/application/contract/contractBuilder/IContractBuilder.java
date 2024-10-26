package com.digisphere.propertize.application.contract.contractBuilder;

import com.digisphere.propertize.application.contract.Contract;
import com.digisphere.propertize.application.contract.component.ContractStatus;

import java.time.LocalDate;
import java.util.UUID;

public interface IContractBuilder {
    Contract build();
    void reset();
    void setId(UUID id);
    void setPropertyId(UUID propertyId);
    void setTenantId(UUID tenantId);
    void setStartDate(LocalDate startDate);
    void setEndDate(LocalDate endDate);
    void setMonthlyRent(Double monthlyRent);
    void setPaymentDueDay(Integer paymentDueDay);
    void setSecurityDeposit(Double securityDeposit);
    void setStatus(ContractStatus status);
    void setTerminationFee(Double terminationFee);
    void setRenewable(Boolean isRenewable);
    void setAutoRenewal(Boolean autoRenewal);
    void setMaintenanceClause(String maintenanceClause);
    void setSubleasingAllowed(Boolean subleasingAllowed);
    void setContractTerms(String contractTerms);
    void setTerminationDate(LocalDate terminationDate);
    void setTerminationReason(String terminationReason);

}

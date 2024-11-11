package com.digisphere.propertize.application.director.TemplateMethodPattern.specificMethods;

import com.digisphere.propertize.application.contract.contractBuilder.ContractBuilder;
import com.digisphere.propertize.application.contract.contractBuilder.IContractBuilder;
import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.application.contract.domain.component.ContractStatus;
import com.digisphere.propertize.application.contract.utils.CalculateContractPeriod;
import com.digisphere.propertize.application.contract.utils.GenerateContractTerms;
import com.digisphere.propertize.application.contract.utils.GenerateMaintenanceClause;
import com.digisphere.propertize.application.contract.utils.TerminationFeePercentCalculate;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class ContractDirectorImpl implements IDirector {
    private final IContractBuilder contractBuilder = new ContractBuilder();
    private Contract contract;

    @Override
    public void buildEntity(Map<String, String> data) {
        var startDate = LocalDate.parse(data.get("startDate"));
        var period = Integer.parseInt(data.get("period"));
        var monthlyRent = Double.valueOf(data.get("monthlyRent"));
        var paymentDueDay = Integer.parseInt(data.get("paymentDueDay"));
        var securityDeposit = Double.valueOf(data.get("securityDeposit"));

        contractBuilder.setId(UUID.randomUUID());
        contractBuilder.setPropertyId(UUID.fromString(data.get("propertyId")));
        contractBuilder.setTenantId(UUID.fromString(data.get("tenantId")));
        contractBuilder.setStartDate(startDate);
        contractBuilder.setEndDate(CalculateContractPeriod.calculate(startDate, period));
        contractBuilder.setMaintenanceClause(GenerateMaintenanceClause.generate());
        contractBuilder.setMonthlyRent(monthlyRent);
        contractBuilder.setPaymentDueDay(paymentDueDay);
        contractBuilder.setSecurityDeposit(securityDeposit);
        contractBuilder.setTerminationFee(TerminationFeePercentCalculate.calculate(period));
        contractBuilder.setStatus(ContractStatus.ACTIVE);
        contractBuilder.setContractTerms(GenerateContractTerms.generate(data.get("address"), period, startDate, CalculateContractPeriod.calculate(startDate, period), monthlyRent, paymentDueDay, securityDeposit));
        contract = contractBuilder.build();
    }

    @Override
    public <T> T get() {
        return (T) contract;
    }
}

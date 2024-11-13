package com.digisphere.propertize.adapter.dtos.contract;

public record OutputContractDto(
        String id,
        String propertyId,
        String tenantCpf,
        String startDate,
        String endDate,
        String monthlyRent,
        String paymentDueDay,
        String securityDeposit,
        String status
) {
}

package com.digisphere.propertize.adapter.dtos.contract;

import jakarta.validation.constraints.NotBlank;

public record CreateContractDto(
        @NotBlank
        String propertyId,
        @NotBlank
        String tenantCpf,
        @NotBlank
        String period,
        @NotBlank
        String paymentDueDay,
        @NotBlank
        String securityDeposit
) {
}

package com.digisphere.propertize.adapter.dtos.contract;

import jakarta.validation.constraints.NotBlank;

public record ContractUpdateRequestDto(
        @NotBlank
        String id,
        @NotBlank
        String terminationDate,
        @NotBlank
        String terminationReason
) {
}

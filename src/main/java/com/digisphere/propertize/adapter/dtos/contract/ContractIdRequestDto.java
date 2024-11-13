package com.digisphere.propertize.adapter.dtos.contract;

import jakarta.validation.constraints.NotBlank;

public record ContractIdRequestDto(
        @NotBlank
        String id
) {
}

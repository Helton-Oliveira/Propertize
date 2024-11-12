package com.digisphere.propertize.adapter.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record UserReferenceDto(
        @NotBlank(message = "ERRO! CPF obrigatório para atualização")
        String cpf,
        String password,
        String phone
) {
}

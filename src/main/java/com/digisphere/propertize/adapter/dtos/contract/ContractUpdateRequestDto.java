package com.digisphere.propertize.adapter.dtos.contract;

import jakarta.validation.constraints.NotBlank;

public record ContractUpdateRequestDto(
        @NotBlank (message = "ERRO! IMPOSSÍVEL ENCERRAR CONTRATO SEM DENIFIR O IDENTIFICADOR")
        String id,
        @NotBlank (message = "ERRO! IMPOSSÍVEL ENCERRAR CONTRATO SEM DENIFIR A DATA DE TÉRMINO")
        String terminationDate,
        @NotBlank (message = "ERRO! IMPOSSÍVEL ENCERRAR CONTRATO SEM DENIFIR A RAZÃO DO ENCERRAMENTO DO CONTRATO")
        String terminationReason
) {
}

package com.digisphere.propertize.adapter.dtos.contract;

import jakarta.validation.constraints.NotBlank;

public record CreateContractDto(
        @NotBlank (message = "ERRO! IMPOSSÍVEL CRIAR CONTRATO SEM DENIFIR O IDENTIFICADOR DA PROPRIEDADE")
        String propertyId,
        @NotBlank (message = "ERRO! IMPOSSÍVEL CRIAR CONTRATO SEM DENIFIR O IDENTIFICADOR DO INQUILINO")
        String tenantCpf,
        @NotBlank(message = "ERRO! IMPOSSÍVEL CRIAR CONTRATO SEM DENIFIR O PERIODO")
        String period,
        @NotBlank (message = "ERRO! IMPOSSÍVEL CRIAR CONTRATO SEM DENIFIR O DIA DO VENCIMENTO DO PAGAMENTO")
        String paymentDueDay,
        @NotBlank (message = "ERRO! IMPOSSÍVEL CRIAR CONTRATO SEM DENIFIR O DEPOSITO ADIANTADO")
        String securityDeposit
) {
}

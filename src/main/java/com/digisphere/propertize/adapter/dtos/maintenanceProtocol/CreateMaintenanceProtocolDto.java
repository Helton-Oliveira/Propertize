package com.digisphere.propertize.adapter.dtos.maintenanceProtocol;

import jakarta.validation.constraints.NotBlank;

public record CreateMaintenanceProtocolDto(
        @NotBlank(message = "ERRO! IMPOSSÍVEL CRIAR PROTOCOLO SEM UM ID DE PROPRIEDADE")
        String propertyId,
        @NotBlank (message = "ERRO! IMPOSSÍVEL CRIAR PROTOCOLO SEM DENIFIR O CPF DO INQUILINO QUE REALIZOU O CHAMADO")
        String tenantCpf,
        @NotBlank (message = "ERRO! IMPOSSÍVEL CRIAR PROTOCOLO SEM DEFINIR OS DETALHES DE MANUTENÇÃO")
        String maintenanceDetails
) {
}

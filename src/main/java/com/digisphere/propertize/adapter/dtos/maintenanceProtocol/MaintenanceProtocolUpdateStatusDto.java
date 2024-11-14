package com.digisphere.propertize.adapter.dtos.maintenanceProtocol;

import jakarta.validation.constraints.NotBlank;

public record MaintenanceProtocolUpdateStatusDto(
        @NotBlank(message = "ERRO! IMPOSSÍVEL ATUALIZAR STATUS DO PROTOCOLO SEM DENIFIR O IDENTIFICADOR")
        String protocol,
        @NotBlank (message = "ERRO! IMPOSSÍVEL ATUALIZAR PROTOCOLO SEM DENIFIR O STATUS")
        String status
) {
}

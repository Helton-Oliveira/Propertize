package com.digisphere.propertize.adapter.dtos.maintenanceProtocol;

import jakarta.validation.constraints.NotBlank;

public record ClosedMaintenanceProtocolDto(
        @NotBlank (message = "ERRO! IMPOSSÍVEL ENCERRAR PROTOCOLO SEM O NUMERO DE IDENTIFICAÇÃO")
        String protocol,
        @NotBlank (message = "ERRO! IMPOSSÍVEL ENCERRAR PROTOCOLO SEM DENIFIR O STATUS")
        String status,
        @NotBlank (message = "ERRO! IMPOSSÍVEL ENCERRAR PROTOCOLO SEM DEFINIR O MOTIVO")
        String reason
) {
}

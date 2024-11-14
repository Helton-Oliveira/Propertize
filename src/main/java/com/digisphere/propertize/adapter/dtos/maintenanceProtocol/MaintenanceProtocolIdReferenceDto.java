package com.digisphere.propertize.adapter.dtos.maintenanceProtocol;

import jakarta.validation.constraints.NotBlank;

public record MaintenanceProtocolIdReferenceDto(
    @NotBlank (message = "ERRO! IMPOSSÍVEL BUSCAR PROTOCOLO SEM DENIFIR O NUMERO DE IDENTIFICAÇÃO")
    String protocol
) {
}

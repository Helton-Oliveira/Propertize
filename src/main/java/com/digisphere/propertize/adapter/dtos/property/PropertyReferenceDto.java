package com.digisphere.propertize.adapter.dtos.property;

import jakarta.validation.constraints.NotBlank;

public record PropertyReferenceDto(
        @NotBlank(message = "ERRO! NÃO É POSSÍVEL ATUALIZAR A PROPRIEDADE COM ID NULO")
        String id,

        String rentValue
) {
}

package com.digisphere.propertize.adapter.dtos.property;

import jakarta.validation.constraints.NotBlank;

public record CreatePropertyDto(
        @NotBlank (message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM CPF DE PROPRIETÁRIO NULO")
         String ownerCpf,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM O CAMPO DESCRICAO NULO")
         String description,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM O CAMPO TIPO NULO")
         String type,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM O CAMPO DE ÁREA NULO")
         String size,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM O CAMPO NUMERO DE QUARTOS NULO")
         String bedroomCount,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM O CAMPO NUMERO DE BANHEIROS NULO")
         String bathroomCount,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM O CAMPO GARAGEM NULO")
         String hasGarage,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM ALUGUEL NULO")
         String rentValue,
         String constructionDate,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE SEM A CAMPO DE RUA")
         String street,
         String number,
         String complement,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM BAIRRO NULO")
         String neighborhood,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM CIDADE COMO NULO")
         String city,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM ESTADO NULO")
         String state,
         @NotBlank(message = "ERRO! NÃO É POSSÍVEL CRIAR PROPRIEDADE COM CEP NULO")
         String postalCode
) {
}

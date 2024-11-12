package com.digisphere.propertize.adapter.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank (message = "ERRO! NOME OBRIGATÓRIO PARA CADASTRO")
        String name,

        @NotBlank @Email (message = "ERRO! EMAIL NÃO PODE SER NULO OU INVÁLIDO")
        String email,

        @NotBlank(message = "ERRO! CPF OBRIGATÓRIO PARA CADASTRO")
        String cpf,

        @NotBlank(message = "ERRO! TELEFONE OBRIGATÓRIO PARA CADASTRO")
        String phone,

        @NotBlank(message = "ERRO! CATEGORIA OBRIGATÓRIO PARA CADASTRO")
        String role
) {
}

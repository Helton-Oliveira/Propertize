package com.digisphere.propertize;

import com.digisphere.propertize.user.useCase.CreateUser;
import com.digisphere.propertize.user.useCase.strategyPattern.context.Context;
import com.digisphere.propertize.user.useCase.strategyPattern.context.IContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    @DisplayName("Deve criar um usuario admnistrador")
    void createAdmin() {

        Map<String, String> input = new HashMap<>();
        IContext context = new Context();

        input.put("name", "Richard Robson");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");

        var admin = new CreateUser(context);
        var result = admin.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("ADMIN");
        assertThat(result.getEmail()).isEqualTo("richard.robson@propertize.com");
    }

    @Test
    @DisplayName("Deve criar um usuario inquilino")
    void createTenant() {
        Map<String, String> input = new HashMap<>();
        IContext context = new Context();

        input.put("name", "Edina Moura");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");
        input.put("email", "edinaMoura@gmail.com");
        input.put("role", "tenant");

        var tenant = new CreateUser(context);
        var result = tenant.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("TENANT");
    }

    @Test
    @DisplayName("Deve criar um usuario proprietario de uma residencia")
    void createOwner() {
        Map<String, String> input = new HashMap<>();
        IContext context = new Context();

        input.put("name", "Edina Moura");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");
        input.put("email", "edinaMoura@gmail.com");
        input.put("role", "owner");

        var tenant = new CreateUser(context);
        var result = tenant.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("OWNER");
    }
}

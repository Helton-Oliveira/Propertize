package com.digisphere.propertize;

import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import com.digisphere.propertize.application.user.useCase.CreateUser;
import com.digisphere.propertize.application.user.useCase.GetOneUser;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.Context;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.IContext;
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
        IRepositoryContext stateContext = new RepositoryContext();

        input.put("name", "Richard Robson");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");

        var admin = new CreateUser(stateContext, context);
        var result = admin.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("ADMIN");
        assertThat(result.getEmail()).isEqualTo("richard.robson@propertize.com");
    }

    @Test
    @DisplayName("Deve criar um usuario inquilino")
    void createTenant() {
        Map<String, String> input = new HashMap<>();
        IContext context = new Context();
        IRepositoryContext stateContext = new RepositoryContext();

        input.put("name", "Edina Moura");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");
        input.put("email", "edinaMoura@gmail.com");
        input.put("role", "tenant");

        var tenant = new CreateUser(stateContext, context);
        var result = tenant.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("TENANT");
    }

    @Test
    @DisplayName("Deve criar um usuario proprietario de uma residencia")
    void createOwner() {
        Map<String, String> input = new HashMap<>();
        IContext context = new Context();
        IRepositoryContext stateContext = new RepositoryContext();

        input.put("name", "Edina Moura");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");
        input.put("email", "edinaMoura@gmail.com");
        input.put("role", "owner");

        var tenant = new CreateUser(stateContext, context);
        var result = tenant.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("OWNER");
    }

    @Test
    @DisplayName("Deve retornar um usuario admin")
    void getOneAdmin() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var get = new GetOneUser(repositoryContext);
        var user = get.execute("42a1576f-4b10-467f-831c-b91b5798500b");

        assertThat(user.getRole().toString()).isEqualTo("ADMIN");
    }
}

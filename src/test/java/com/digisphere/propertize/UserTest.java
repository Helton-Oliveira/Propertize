package com.digisphere.propertize;

import com.digisphere.propertize.application.observerPattern.EventManager;
import com.digisphere.propertize.application.observerPattern.IEventManager;
import com.digisphere.propertize.application.user.useCase.*;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.Context;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.IContext;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
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
        IEventManager eventManager = new EventManager();

        input.put("name", "Richard Robson");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");

        var admin = new CreateUser(stateContext, context, eventManager);
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
        IEventManager eventManager = new EventManager();

        input.put("name", "Helton Oliveira");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");
        input.put("email", "heltonhenriqueoliveira321@gmail.com");
        input.put("role", "tenant");

        var tenant = new CreateUser(stateContext, context, eventManager);
        var result = tenant.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("TENANT");
    }

    @Test
    @DisplayName("Deve criar um usuario proprietario de uma residencia")
    void createOwner() {
        Map<String, String> input = new HashMap<>();
        IContext context = new Context();
        IRepositoryContext stateContext = new RepositoryContext();
        IEventManager eventManager = new EventManager();
        input.put("name", "Diego HDM");
        input.put("cpf", "1234567890");
        input.put("phone", "19 99845577");
        input.put("email", "hdm@gmail.com");
        input.put("role", "owner");

        var tenant = new CreateUser(stateContext, context, eventManager);
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

    @Test
    @DisplayName("Deve retornar todos os usuários")
    void getAllUsers() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var getAllUsers = new GetAllUsers(repositoryContext);
        var userList = getAllUsers.execute();

        userList.forEach(u -> {
            assertThat(u.getActive()).isTrue();
        });
    }

    @Test
    @DisplayName("Deve atualizar a senha do usuario")
    void updatePassword() {
        Map<String, String> data = new HashMap<>();
        data.put("password", "d_X3");
        IRepositoryContext repositoryContext = new RepositoryContext();

        var updatedData = new UpdateUser(repositoryContext);
        var response = updatedData.execute("7193ef6b-bffc-444d-a25e-d7966a5b6b69", data);

        assertThat(response).isEqualTo("PASSWORD DO USUÁRIO COM ID: 7193ef6b-bffc-444d-a25e-d7966a5b6b69 ATUALIZADA COM SUCESSO!");
    }

    @Test
    @DisplayName("Deve atualizar o telefone do usuario")
    void updatePhone() {
        Map<String, String> data = new HashMap<>();
        data.put("phone", "19 9999-0000");
        IRepositoryContext repositoryContext = new RepositoryContext();

        var updatedData = new UpdateUser(repositoryContext);
        var response = updatedData.execute("7193ef6b-bffc-444d-a25e-d7966a5b6b69", data);

        assertThat(response).isEqualTo("PHONE DO USUÁRIO COM ID: 7193ef6b-bffc-444d-a25e-d7966a5b6b69 ATUALIZADA COM SUCESSO!");
    }

    @Test
    @DisplayName("Deve desativar usuario")
    void deleteUser() {
        IRepositoryContext repositoryContext = new RepositoryContext();
        var disabledUser = new DisableUser(repositoryContext);
        var response = disabledUser.execute("7193ef6b-bffc-444d-a25e-d7966a5b6b69");

        assertThat(response).isEqualTo("USUÁRIO COM ID: 7193ef6b-bffc-444d-a25e-d7966a5b6b69 DESAIVADO COM SUCESSO.");
    }
}

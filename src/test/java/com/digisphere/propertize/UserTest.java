package com.digisphere.propertize;

import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.TemplateMethodDirector;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.observerPattern.subject.EventManager;
import com.digisphere.propertize.application.observerPattern.subject.IEventManager;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.*;
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
        ITemplateMethod abstractDirector = new TemplateMethodDirector();
        IRepositoryContext stateContext = new RepositoryContext();
        IEventManager eventManager = new EventManager();

        input.put("name", "Richard Robson");
        input.put("cpf", "58158076050");
        input.put("phone", "19 99845577");

        var admin = new CreateUser(stateContext, eventManager, abstractDirector);
        var result = admin.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("ADMIN");
        assertThat(result.getEmail()).isEqualTo("richard.robson@propertize.com");
    }

    @Test
    @DisplayName("Deve criar um usuario inquilino")
    void createTenant() {
        Map<String, String> input = new HashMap<>();
        ITemplateMethod abstractDirector = new TemplateMethodDirector();
        IRepositoryContext stateContext = new RepositoryContext();
        IEventManager eventManager = new EventManager();

        input.put("name", "Helton Oliveira");
        input.put("cpf", "07416672074");
        input.put("phone", "19 99845577");
        input.put("email", "heltonhenriqueoliveira321@gmail.com");
        input.put("role", "tenant");

        var tenant = new CreateUser(stateContext, eventManager, abstractDirector);
        var result = tenant.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("TENANT");
    }

    @Test
    @DisplayName("Deve criar um usuario proprietario de uma residencia")
    void createOwner() {
        Map<String, String> input = new HashMap<>();
        ITemplateMethod abstractDirector = new TemplateMethodDirector();
        IRepositoryContext stateContext = new RepositoryContext();
        IEventManager eventManager = new EventManager();
        input.put("name", "Karla Maynny");
        input.put("cpf", "71686187092");
        input.put("phone", "19 99845577");
        input.put("email", "karlamaynny2017@gmail.com");
        input.put("role", "owner");

        var tenant = new CreateUser(stateContext, eventManager, abstractDirector);
        var result = tenant.execute(input);

        assertThat(result.getRole().toString()).isEqualTo("OWNER");
    }

    @Test
    @DisplayName("Deve retornar um usuario admin")
    void getOneAdmin() {
        IRepositoryContext repositoryContext = new RepositoryContext();

        var get = new GetOneUser(repositoryContext);
        User user = get.execute("58158076050");

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
        var response = updatedData.execute("58158076050", data);

        assertThat(response).isEqualTo("PASSWORD DO USUÁRIO COM CPF: 58158076050 ATUALIZADA COM SUCESSO!");
    }

    @Test
    @DisplayName("Deve atualizar o telefone do usuario")
    void updatePhone() {
        Map<String, String> data = new HashMap<>();
        data.put("phone", "19 9999-0000");
        IRepositoryContext repositoryContext = new RepositoryContext();

        var updatedData = new UpdateUser(repositoryContext);
        var response = updatedData.execute("58158076050", data);

        assertThat(response).isEqualTo("PHONE DO USUÁRIO COM CPF: 58158076050 ATUALIZADA COM SUCESSO!");
    }

    @Test
    @DisplayName("Deve desativar usuario")
    void deleteUser() {
        IRepositoryContext repositoryContext = new RepositoryContext();
        var disabledUser = new DisableUser(repositoryContext);
        var response = disabledUser.execute("58158076050");

        assertThat(response).isEqualTo("USUÁRIO COM CPF: 58158076050 DESAIVADO COM SUCESSO.");
    }
}

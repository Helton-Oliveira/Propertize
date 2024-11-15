package com.digisphere.propertize.IntegrationTests;

import com.digisphere.propertize.adapter.adapterPattern.userAdapter.IAdapterUser;
import com.digisphere.propertize.adapter.adapterPattern.userAdapter.UserAdapter;
import com.digisphere.propertize.adapter.dtos.user.CreateUserDto;
import com.digisphere.propertize.adapter.dtos.user.OutputUser;
import com.digisphere.propertize.adapter.dtos.user.UserReferenceDto;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.TemplateMethodDirector;
import com.digisphere.propertize.application.observerPattern.subject.EventManager;
import com.digisphere.propertize.application.observerPattern.subject.IEventManager;
import com.digisphere.propertize.application.user.businessRules.IUserRules;
import com.digisphere.propertize.application.user.businessRules.ShouldNotBeDeletedIfItIsTheOnlyOne;
import com.digisphere.propertize.application.user.useCase.CreateUser;
import com.digisphere.propertize.application.user.useCase.DisableUser;
import com.digisphere.propertize.application.user.useCase.GetOneUser;
import com.digisphere.propertize.application.user.useCase.UpdateUser;
import com.digisphere.propertize.application.user.useCase.interfaces.ICreateUser;
import com.digisphere.propertize.application.user.useCase.interfaces.IDisableUser;
import com.digisphere.propertize.application.user.useCase.interfaces.IGetOneUSer;
import com.digisphere.propertize.application.user.useCase.interfaces.IUpdateUser;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAdapterTest {

    private ICreateUser createUser;
    private IGetOneUSer getOneUSer;
    private IDisableUser disableUser;
    private IUpdateUser updateUser;
    private IRepositoryContext repositoryContext;
    private IEventManager eventManager;
    private ITemplateMethod templateMethod;
    private IAdapterUser adapterUser;
    private List<IUserRules> userRules = new ArrayList<>();

    @BeforeEach
    public void setup() {
        repositoryContext = new RepositoryContext();
        userRules.add(new ShouldNotBeDeletedIfItIsTheOnlyOne());
        eventManager = new EventManager();
        templateMethod = new TemplateMethodDirector();
        createUser = new CreateUser(repositoryContext, eventManager, templateMethod);
        getOneUSer = new GetOneUser(repositoryContext);
        updateUser = new UpdateUser(repositoryContext);
        disableUser = new DisableUser(repositoryContext, userRules);

        adapterUser = new UserAdapter(createUser, updateUser, getOneUSer, disableUser);
    }

    @Test
    @DisplayName("Deve adaptar a requisicao de criar um usuário")
    void adapterToCreateUsers() {
        var dto = new CreateUserDto("Peter Parker", "peter@email.com", "45732543074", "19 8577-3341", "admin");
        OutputUser user = adapterUser.adaptCreateUserRequest(dto);

       assertThat(user.email()).isEqualTo("peter.parker@propertize.com");
    }

    @Test
    @DisplayName("Deve adaptar a requisicao de atualizar senha de um usuário")
    void adapterToUpdateUserPassword() {
        var dto = new UserReferenceDto("58158076050", "senhaForte123", null);
        String response = adapterUser.adaptUpdateUserRequest(dto);

        assertThat(response).isEqualTo("PASSWORD DO USUÁRIO COM CPF: 58158076050 ATUALIZADA COM SUCESSO!");
    }

    @Test
    @DisplayName("Deve adaptar a requisicao de atualizar telefone de um usuário")
    void adapterToUpdateUserPhone() {
        var dto = new UserReferenceDto("58158076050", "avenger", "25 2222-3333");

        String response = adapterUser.adaptUpdateUserRequest(dto);

        assertThat(response).isEqualTo("PHONE DO USUÁRIO COM CPF: 58158076050 ATUALIZADA COM SUCESSO!");
    }
}

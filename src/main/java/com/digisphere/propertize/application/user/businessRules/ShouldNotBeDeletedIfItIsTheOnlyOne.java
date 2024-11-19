package com.digisphere.propertize.application.user.businessRules;

import com.digisphere.propertize.application.user.domain.Role;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.infra.ErrorHandler.CustomException;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.List;

public class ShouldNotBeDeletedIfItIsTheOnlyOne implements IUserRules{

    @Override
    public void valid(IRepositoryContext repositoryContext) {
        repositoryContext.changeState("users");
        List<User> users = repositoryContext.getAll();

        if (users.isEmpty()) throw new CustomException("ERRO! NENHUM REGISTRO ENCONTRADO.");

        var admins = users.stream()
                .filter(u -> u.getRole().equals(Role.ADMIN) && u.getActive().equals(true))
                .toList();

        if (admins.isEmpty()) {
            throw new CustomException("ERRO! DEVE EXISTIR PELO MENOS 1 ADMINISTRADOR NO SISTEMA");
        }
    }
}

package com.digisphere.propertize.application.user.useCase;

import com.digisphere.propertize.application.user.useCase.interfaces.IUpdateUser;
import com.digisphere.propertize.application.user.utils.PasswordEncryptUtil;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.Map;

public class UpdateUser implements IUpdateUser {

    private final IRepositoryContext repositoryContext;

    public UpdateUser(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public String execute(String id, Map<String, String> data) {
        repositoryContext.changeState("users");
        if(data.containsKey("password")) data.put("password", PasswordEncryptUtil.execute(data.get("password")));
        return repositoryContext.update(id, data);
    }
}

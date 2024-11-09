package com.digisphere.propertize.application.contract.useCase;

import com.digisphere.propertize.application.contract.useCase.interfaces.IUpdateContract;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.Map;

public class UpdateContract implements IUpdateContract {
    private final IRepositoryContext repositoryContext;

    public UpdateContract(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public String execute(String id, Map<String, String> data) {
        repositoryContext.changeState("contracts");
        return repositoryContext.update(id, data);
    }
}

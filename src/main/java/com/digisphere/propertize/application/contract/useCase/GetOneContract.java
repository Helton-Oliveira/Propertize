package com.digisphere.propertize.application.contract.useCase;

import com.digisphere.propertize.application.contract.Contract;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public class GetOneContract implements IGetOneContract{

    private final IRepositoryContext repositoryContext;

    public GetOneContract(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public Contract execute(String id) {
        repositoryContext.changeState("contracts");
        Contract contract = repositoryContext.getOne(id);
        return contract;
    }
}

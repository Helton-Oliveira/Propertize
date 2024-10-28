package com.digisphere.propertize.application.contract.useCase;

import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.List;

public class GetAllContracts implements IGetAllContracts{

    private final IRepositoryContext repositoryContext;

    public GetAllContracts(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public List<Contract> execute() {
        repositoryContext.changeState("contracts");
        return repositoryContext.getAll().stream()
                .map(c -> (Contract) c)
                .toList();
    }
}

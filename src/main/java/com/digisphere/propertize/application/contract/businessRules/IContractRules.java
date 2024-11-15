package com.digisphere.propertize.application.contract.businessRules;

import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public interface IContractRules {
    void valid(Contract contract, IRepositoryContext repositoryContext);
}

package com.digisphere.propertize.application.contract.businessRules;

import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.application.user.domain.Role;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public class TenantContractValidator implements IContractRules {
    @Override
    public void valid(Contract contract, IRepositoryContext repositoryContext) {
        repositoryContext.changeState("users");
        User tenant = repositoryContext.getOne(contract.getTenantCpf());

        if(!tenant.getRole().equals(Role.TENANT)) throw new RuntimeException("ERRO AO CRIAR CONTRATO! SÓ É POSSÍVEL VINCULAR INQUILINOS AOS CONTRATOS");

        repositoryContext.changeState("properties");
    }
}

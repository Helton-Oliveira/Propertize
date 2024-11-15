package com.digisphere.propertize.application.property.businessRules;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public class CheckIfTheOwnerIsActive implements IPropertyRules{
    private final IRepositoryContext repositoryContext;

    public CheckIfTheOwnerIsActive(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public void valid(Property property) {
        repositoryContext.changeState("users");
        User owner = repositoryContext.getOne(property.getOwnerCpf());
        if (owner == null || owner.getActive().equals(false) || !owner.getRole().name().equals("OWNER")) throw new RuntimeException("ERRO! PROPRIETÁRIO NÃO EXISTENTE NO SISTEMA.");
    }
}

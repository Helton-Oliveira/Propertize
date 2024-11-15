package com.digisphere.propertize.application.user.businessRules;

import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public interface IUserRules {
    void valid(IRepositoryContext repositoryContext);
}

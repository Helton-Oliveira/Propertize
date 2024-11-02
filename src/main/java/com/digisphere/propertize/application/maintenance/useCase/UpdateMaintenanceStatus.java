package com.digisphere.propertize.application.maintenance.useCase;

import com.digisphere.propertize.application.maintenance.useCase.interaces.IUpdateMaintenanceStatus;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.Map;

public class UpdateMaintenanceStatus implements IUpdateMaintenanceStatus {

    private final IRepositoryContext repositoryContext;

    public UpdateMaintenanceStatus(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public String execute(String protocol, Map<String, String> data) {
        repositoryContext.changeState("maintenanceProtocols");
        return repositoryContext.update(protocol, data);
    }
}

package com.digisphere.propertize.application.maintenance.useCase;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IGetOneMaintenanceProtocol;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

public class GetOneMaintenanceProtocol implements IGetOneMaintenanceProtocol {

    private final IRepositoryContext repositoryContext;

    public GetOneMaintenanceProtocol(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public MaintenanceProtocol execute(String protocol) {
        repositoryContext.changeState("maintenanceProtocols");
        return repositoryContext.getOne(protocol);
    }
}

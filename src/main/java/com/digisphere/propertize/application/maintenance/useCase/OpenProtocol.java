package com.digisphere.propertize.application.maintenance.useCase;

import com.digisphere.propertize.application.director.Director;
import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IOpenProtocol;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.Map;

public class OpenProtocol implements IOpenProtocol {
    private final IRepositoryContext repositoryContext;

    public OpenProtocol(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public MaintenanceProtocol execute(Map<String, String> data) {
        var creator = Director.createMaintenanceProtocolDirector();
        creator.createMaintenanceProtocol(data);

        return creator.buildMaintenanceProtocol();
    }
}

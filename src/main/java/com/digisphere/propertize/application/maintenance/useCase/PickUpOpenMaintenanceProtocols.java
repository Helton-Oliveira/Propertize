package com.digisphere.propertize.application.maintenance.useCase;

import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IPickUpOpenMaintenanceProtocols;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.sun.tools.javac.Main;

import java.util.List;

public class PickUpOpenMaintenanceProtocols implements IPickUpOpenMaintenanceProtocols {
    private final IRepositoryContext repositoryContext;

    public PickUpOpenMaintenanceProtocols(IRepositoryContext repositoryContext) {
        this.repositoryContext = repositoryContext;
    }

    @Override
    public List<MaintenanceProtocol> execute() {
        repositoryContext.changeState("maintenanceProtocols");
        return repositoryContext.getAll().stream()
                .map(p -> (MaintenanceProtocol) p)
                .toList();
    }
}

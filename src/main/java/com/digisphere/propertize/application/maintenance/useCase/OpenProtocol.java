package com.digisphere.propertize.application.maintenance.useCase;

import com.digisphere.propertize.application.director.Director;
import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IOpenProtocol;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.HashMap;
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
        var protocol = creator.buildMaintenanceProtocol();

        repositoryContext.changeState("maintenanceProtocols");
        Map<String, Object> protocolData = new HashMap<>();
        protocolData.put("protocol", protocol);
        var isSaved = repositoryContext.save(protocolData);

        if(!isSaved) throw new RuntimeException("ERRO AO SALVAR PROTOCOLO DE MANUTENCAO");
        return protocol;
    }
}

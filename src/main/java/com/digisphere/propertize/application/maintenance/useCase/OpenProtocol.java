package com.digisphere.propertize.application.maintenance.useCase;

import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.maintenance.domain.MaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IOpenProtocol;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.util.HashMap;
import java.util.Map;

public class OpenProtocol implements IOpenProtocol {
    private final IRepositoryContext repositoryContext;
    private final ITemplateMethod abstractDirector;

    public OpenProtocol(IRepositoryContext repositoryContext, ITemplateMethod abstractDirector) {
        this.repositoryContext = repositoryContext;
        this.abstractDirector = abstractDirector;
    }

    @Override
    public MaintenanceProtocol execute(Map<String, String> data) {
        MaintenanceProtocol protocol = abstractDirector.build(data);

        repositoryContext.changeState("maintenanceProtocols");
        Map<String, Object> protocolData = new HashMap<>();
        protocolData.put("protocol", protocol);
        var isSaved = repositoryContext.save(protocolData);

        if(!isSaved) throw new RuntimeException("ERRO AO SALVAR PROTOCOLO DE MANUTENCAO");
        return protocol;
    }
}

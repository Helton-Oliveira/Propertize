package com.digisphere.propertize.application.contract.useCase;

import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.application.contract.useCase.interfaces.ICreateContract;
import com.digisphere.propertize.application.director.bridgePattern.abstractions.IAbstractDirector;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CreateContract implements ICreateContract {

    private final IRepositoryContext repositoryContext;
    private final IAbstractDirector abstractDirector;

    public CreateContract(IRepositoryContext repositoryContext, IAbstractDirector abstractDirector) {
        this.repositoryContext = repositoryContext;
        this.abstractDirector = abstractDirector;
    }

    @Override
    public Contract execute(Map<String, String> data) {
        repositoryContext.changeState("properties");
        Property property = repositoryContext.getOne(data.get("propertyId"));
        Map <String, String> updatePropertyStatus = new HashMap<>();
        updatePropertyStatus.put("status", "alugado");
        repositoryContext.update(property.getId().toString(), updatePropertyStatus);

        repositoryContext.changeState("users");
        User user = repositoryContext.getOne(data.get("tenantId"));

        if(!user.getRole().toString().equalsIgnoreCase("tenant")) throw new RuntimeException("ERRO SÓ É POSSÍVEL VINCULAR INQUILINOS AOS CONTRATOS");

        data.put("propertyId", property.getId().toString());
        data.put("ownerId", user.getId().toString());
        data.put("startDate", LocalDate.now().toString());
        data.put("monthlyRent", property.getRentValue().toString());
        data.put("address", property.getAddress().toString());

        Contract contract = abstractDirector.build(data);
        Map<String, Object> contractMap = new HashMap<>();
        contractMap.put("contract", contract);

        repositoryContext.changeState("contracts");
        var isSaved = repositoryContext.save(contractMap);
        if(!isSaved) throw new RuntimeException("ERRO AO SALVAR CONTRATO");

        return contract;
    }
}

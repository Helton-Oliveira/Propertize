package com.digisphere.propertize.application.director.bridgePattern.implementations;

import com.digisphere.propertize.application.director.bridgePattern.abstractions.IAbstractDirector;

import java.util.Map;

public class AbstractDirector implements IAbstractDirector {
    private com.digisphere.propertize.application.director.bridgePattern.abstractions.IBridge IBridge;

    @Override
    public <T> T build(Map<String, String> data) {
        changeImplementation(data);
        IBridge.build(data);
        return IBridge.get();
    }

    private void changeImplementation(Map<String, String> data) {
        if(data.containsKey("name")) this.IBridge = new UserDirectorImpl();
        if(data.containsKey("ownerId")) this.IBridge = new PropertyDirectorImpl();
        if(data.containsKey("tenantId")) this.IBridge = new ContractDirectorImpl();
        if(data.containsKey("MaintenanceDetails")) this.IBridge = new MaintenanceProtocolDirectorImpl();
    }
}

package com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass;

import com.digisphere.propertize.application.director.TemplateMethodPattern.specificMethods.*;

import java.util.Map;

public class TemplateMethodDirector implements ITemplateMethod {
    private IDirector director;

    public <T> T build(Map<String, String> data) {
        setDirector(data);
        director.buildEntity(data);
        return director.get();
    }

    private void setDirector(Map<String, String> data) {
        if(data.containsKey("cpf")) this.director = (new UserDirectorImpl());
        if(data.containsKey("ownerCpf")) this.director = (new PropertyDirectorImpl());
        if(data.containsKey("tenantCpf")) this.director = (new ContractDirectorImpl());
        if(data.containsKey("MaintenanceDetails")) this.director = (new MaintenanceProtocolDirectorImpl());
    }
}

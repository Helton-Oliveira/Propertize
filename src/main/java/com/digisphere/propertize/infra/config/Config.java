package com.digisphere.propertize.infra.config;

import com.digisphere.propertize.application.contract.useCase.*;
import com.digisphere.propertize.application.maintenance.useCase.GetOneMaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.OpenProtocol;
import com.digisphere.propertize.application.maintenance.useCase.PickUpOpenMaintenanceProtocols;
import com.digisphere.propertize.application.maintenance.useCase.UpdateMaintenanceStatus;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IGetOneMaintenanceProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IOpenProtocol;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IPickUpOpenMaintenanceProtocols;
import com.digisphere.propertize.application.maintenance.useCase.interaces.IUpdateMaintenanceStatus;
import com.digisphere.propertize.application.observerPattern.subject.EventManager;
import com.digisphere.propertize.application.observerPattern.subject.IEventManager;
import com.digisphere.propertize.application.property.useCase.CreateProperty;
import com.digisphere.propertize.application.property.useCase.GetAllProperties;
import com.digisphere.propertize.application.property.useCase.GetOneProperty;
import com.digisphere.propertize.application.property.useCase.UpdateProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.ICreateProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.IGetAllProperties;
import com.digisphere.propertize.application.property.useCase.interfaces.IGetOneProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.IUpdateProperty;
import com.digisphere.propertize.application.user.useCase.*;
import com.digisphere.propertize.application.user.useCase.interfaces.*;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.Context;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.IContext;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    // infrastructure
    @Bean
    public IRepositoryContext repositoryContext() {
        return new RepositoryContext();
    }

    @Bean
    public IEventManager eventManager() {
        return new EventManager();
    }

    // user use cases
    @Bean
    public IContext context() {
        return new Context();
    }

    @Bean
    public ICreateUser createUser(IRepositoryContext repository, IContext context, IEventManager manager) {
        return new CreateUser(repository, context, manager);
    }

    @Bean
    public IGetOneUSer getOneUser(IRepositoryContext repositoryContext) {
        return new GetOneUser(repositoryContext);
    }

    @Bean
    public IGetAllUsers getAllUsers(IRepositoryContext repositoryContext) {
        return new GetAllUsers(repositoryContext);
    }

    @Bean
    public IDisableUser disableUser(IRepositoryContext repositoryContext) {
        return new DisableUser(repositoryContext);
    }

    @Bean
    public IUpdateUser updateUser(IRepositoryContext repositoryContext) {
        return new UpdateUser(repositoryContext);
    }

    // property use cases
    @Bean
    public ICreateProperty createProperty(IRepositoryContext repositoryContext) {
        return new CreateProperty(repositoryContext);
    }

    @Bean
    public IGetOneProperty getOneProperty(IRepositoryContext repositoryContext) {
        return new GetOneProperty(repositoryContext);
    }

    @Bean
    public IGetAllProperties getAllProperties(IRepositoryContext repositoryContext) {
        return new GetAllProperties(repositoryContext);
    }

    @Bean
    public IUpdateProperty updateProperty(IRepositoryContext repositoryContext) {
        return new UpdateProperty(repositoryContext);
    }

    // contract use cases
    @Bean
    public ICreateContract createContract(IRepositoryContext repositoryContext) {
        return new CreateContract(repositoryContext);
    }

    @Bean
    public IGetAllContracts getAllContracts(IRepositoryContext repositoryContext) {
        return new GetAllContracts(repositoryContext);
    }

    @Bean
    public IGetOneContract getOneContract(IRepositoryContext repositoryContext) {
        return new GetOneContract(repositoryContext);
    }

    @Bean
    public IUpdateContract updateContract(IRepositoryContext repositoryContext) {
        return new UpdateContract(repositoryContext);
    }

    // maintenance use cases
    @Bean
    public IOpenProtocol openProtocol(IRepositoryContext repositoryContext) {
        return new OpenProtocol(repositoryContext);
    }

    @Bean
    public IGetOneMaintenanceProtocol getOneMaintenanceProtocol(IRepositoryContext repositoryContext) {
        return new GetOneMaintenanceProtocol(repositoryContext);
    }

    @Bean
    public IPickUpOpenMaintenanceProtocols pickUpOpenMaintenanceProtocols(IRepositoryContext repositoryContext) {
        return new PickUpOpenMaintenanceProtocols(repositoryContext);
    }

    @Bean
    public IUpdateMaintenanceStatus updateMaintenanceStatus(IRepositoryContext repositoryContext) {
        return new UpdateMaintenanceStatus(repositoryContext);
    }
}

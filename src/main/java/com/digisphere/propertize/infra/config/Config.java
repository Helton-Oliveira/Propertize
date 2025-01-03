package com.digisphere.propertize.infra.config;

import com.digisphere.propertize.adapter.adapterPattern.PropertyAdapter.IPropertyAdapter;
import com.digisphere.propertize.adapter.adapterPattern.PropertyAdapter.PropertyAdapter;
import com.digisphere.propertize.adapter.adapterPattern.contractAdapter.ContractRequestAdapter;
import com.digisphere.propertize.adapter.adapterPattern.contractAdapter.IContractRequestAdapter;
import com.digisphere.propertize.adapter.adapterPattern.maintenanceProtocolAdapter.IMaintenanceProtocolRequestAdapter;
import com.digisphere.propertize.adapter.adapterPattern.maintenanceProtocolAdapter.MaintenanceProtocolRequestAdapter;
import com.digisphere.propertize.adapter.adapterPattern.userAdapter.IAdapterUser;
import com.digisphere.propertize.adapter.adapterPattern.userAdapter.UserAdapter;
import com.digisphere.propertize.application.contract.businessRules.CheckContractCreationDate;
import com.digisphere.propertize.application.contract.businessRules.IContractRules;
import com.digisphere.propertize.application.contract.businessRules.TenantContractValidator;
import com.digisphere.propertize.application.contract.useCase.CreateContract;
import com.digisphere.propertize.application.contract.useCase.GetAllContracts;
import com.digisphere.propertize.application.contract.useCase.GetOneContract;
import com.digisphere.propertize.application.contract.useCase.UpdateContract;
import com.digisphere.propertize.application.contract.useCase.interfaces.ICreateContract;
import com.digisphere.propertize.application.contract.useCase.interfaces.IGetAllContracts;
import com.digisphere.propertize.application.contract.useCase.interfaces.IGetOneContract;
import com.digisphere.propertize.application.contract.useCase.interfaces.IUpdateContract;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.ITemplateMethod;
import com.digisphere.propertize.application.director.TemplateMethodPattern.TemplateClass.TemplateMethodDirector;
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
import com.digisphere.propertize.application.property.businessRules.CheckIfTheOwnerIsActive;
import com.digisphere.propertize.application.property.businessRules.IPropertyRules;
import com.digisphere.propertize.application.property.businessRules.RentalValueMustBeGreatherThanZero;
import com.digisphere.propertize.application.property.useCase.CreateProperty;
import com.digisphere.propertize.application.property.useCase.GetAllProperties;
import com.digisphere.propertize.application.property.useCase.GetOneProperty;
import com.digisphere.propertize.application.property.useCase.UpdateProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.ICreateProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.IGetAllProperties;
import com.digisphere.propertize.application.property.useCase.interfaces.IGetOneProperty;
import com.digisphere.propertize.application.property.useCase.interfaces.IUpdateProperty;
import com.digisphere.propertize.application.user.businessRules.IUserRules;
import com.digisphere.propertize.application.user.businessRules.ShouldNotBeDeletedIfItIsTheOnlyOne;
import com.digisphere.propertize.application.user.useCase.*;
import com.digisphere.propertize.application.user.useCase.interfaces.*;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.Context;
import com.digisphere.propertize.application.user.useCase.strategyPattern.context.IContext;
import com.digisphere.propertize.infra.repository.stateContext.IRepositoryContext;
import com.digisphere.propertize.infra.repository.stateContext.RepositoryContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    @Bean
    public IAdapterUser adapterUser(ICreateUser createUser, IUpdateUser updateUser, IGetOneUSer getOneUSer, IDisableUser disableUser) {
        return new UserAdapter(createUser, updateUser, getOneUSer, disableUser);
    }

    @Bean
    public IPropertyAdapter propertyAdapter(ICreateProperty createProperty, IGetOneProperty getOneProperty, IUpdateProperty updateProperty) {
        return new PropertyAdapter(createProperty, getOneProperty, updateProperty);
    }

    @Bean
    public IContractRequestAdapter contractRequestAdapter(ICreateContract createContract, IGetOneContract getOneContract, IUpdateContract updateContract) {
        return new ContractRequestAdapter(createContract, getOneContract, updateContract);
    }

    @Bean
    public IMaintenanceProtocolRequestAdapter maintenanceProtocolRequestAdapter(IOpenProtocol openProtocol, IGetOneMaintenanceProtocol getOneMaintenanceProtocol, IUpdateMaintenanceStatus updateMaintenanceStatus){
        return new MaintenanceProtocolRequestAdapter(openProtocol, getOneMaintenanceProtocol, updateMaintenanceStatus);
    }

    //BusinessRules
    @Bean
    public IUserRules userRules(){
        return new ShouldNotBeDeletedIfItIsTheOnlyOne();
    }

    @Bean
    public IPropertyRules thanZero() {
        return new RentalValueMustBeGreatherThanZero();
    }

    @Bean
    public IPropertyRules checkIfTheOwnerIsActive(IRepositoryContext repositoryContext) {
        return new CheckIfTheOwnerIsActive(repositoryContext);
    }

    @Bean
    public IContractRules checkContractCreationDate() {
        return new CheckContractCreationDate();
    }

    @Bean
    public IContractRules tenantContractValidator() {
        return new TenantContractValidator();
    }

    //directors
    @Bean
    public ITemplateMethod abstractDirector() {
        return new TemplateMethodDirector();
    }

    // user use cases
    @Bean
    public IContext context() {
        return new Context();
    }

    @Bean
    public ICreateUser createUser(IRepositoryContext repository, ITemplateMethod abstractDirector, IEventManager manager) {
        return new CreateUser(repository, manager, abstractDirector);
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
    public IDisableUser disableUser(IRepositoryContext repositoryContext, List<IUserRules> userRules) {
        return new DisableUser(repositoryContext, userRules);
    }

    @Bean
    public IUpdateUser updateUser(IRepositoryContext repositoryContext) {
        return new UpdateUser(repositoryContext);
    }

    // property use cases
    @Bean
    public ICreateProperty createProperty(IRepositoryContext repositoryContext, ITemplateMethod abstractDirector, List<IPropertyRules> propertyRules) {
        return new CreateProperty(repositoryContext, abstractDirector, propertyRules);
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
    public ICreateContract createContract(IRepositoryContext repositoryContext, ITemplateMethod abstractDirector, List<IContractRules> contractRules) {
        return new CreateContract(repositoryContext, abstractDirector, contractRules);
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
    public IOpenProtocol openProtocol(IRepositoryContext repositoryContext, ITemplateMethod abstractDirector) {
        return new OpenProtocol(repositoryContext, abstractDirector);
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

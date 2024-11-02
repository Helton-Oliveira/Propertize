package com.digisphere.propertize.application.director;

import com.digisphere.propertize.application.contract.domain.Contract;
import com.digisphere.propertize.application.contract.domain.component.ContractStatus;
import com.digisphere.propertize.application.contract.contractBuilder.ContractBuilder;
import com.digisphere.propertize.application.contract.contractBuilder.IContractBuilder;
import com.digisphere.propertize.application.contract.utils.CalculateContractPeriod;
import com.digisphere.propertize.application.contract.utils.GenerateContractTerms;
import com.digisphere.propertize.application.contract.utils.GenerateMaintenanceClause;
import com.digisphere.propertize.application.contract.utils.TerminationFeePercentCalculate;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.domain.component.Address;
import com.digisphere.propertize.application.property.domain.component.PropertyStatus;
import com.digisphere.propertize.application.property.domain.component.PropertyType;
import com.digisphere.propertize.application.property.domain.propertyBuilder.builder.IPropertyBuilder;
import com.digisphere.propertize.application.property.domain.propertyBuilder.builder.PropertyBuilder;
import com.digisphere.propertize.application.user.builderPattern.builder.IUserBuilder;
import com.digisphere.propertize.application.user.builderPattern.builder.UserBuilder;
import com.digisphere.propertize.application.user.domain.Role;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.utils.CorporateEmailCreator;
import com.digisphere.propertize.application.user.utils.RandomPasswordGenerator;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class Director implements IDirector {

    private IUserBuilder userBuilder;
    private IPropertyBuilder propertyBuilder;
    private IContractBuilder contractBuilder;

    public static Director createPropertyDirector() {
        var propertyDirector =  new Director();
        propertyDirector.setPropertyBuilder(new PropertyBuilder());
        return propertyDirector;
    }

    public static Director createUserDirector() {
        var userDirector = new Director();
        userDirector.setUserBuilder(new UserBuilder());
        return userDirector;
    }

    public static Director createContractDirector() {
        var contractDirector = new Director();
        contractDirector.setContractBuilder(new ContractBuilder());
        return contractDirector;
    }

    @Override
    public Property buildProperty() {
        return propertyBuilder.getProperty();
    }

    @Override
    public void createProperty(
            String ownerId,
            String description,
            Map<String, String> address,
            String type,
            Double size,
            Integer bedRoomCount,
            Integer bathRoomCount,
            Boolean hasGarage,
            Double rentValue,
            String status,
            LocalDate constructionDate
    ) {
        propertyBuilder.setId(UUID.randomUUID());
        propertyBuilder.setOwnerId(UUID.fromString(ownerId));
        propertyBuilder.setDescription(description);
        propertyBuilder.setAddress(new Address(address));
        propertyBuilder.setType(PropertyType.fromString(type));
        propertyBuilder.setSize(size);
        propertyBuilder.setBedroomCount(bedRoomCount);
        propertyBuilder.setBathroomCount(bathRoomCount);
        propertyBuilder.setHasGarage(hasGarage);
        propertyBuilder.setRentValue(rentValue);
        propertyBuilder.setStatus(PropertyStatus.fromString(status));
        propertyBuilder.setConstructionDate(constructionDate);
        propertyBuilder.setMaintenancePending(false);
    }

    @Override
    public void createContract(Map<String, String> data){

        var startDate = LocalDate.parse(data.get("startDate"));
        var period = Integer.parseInt(data.get("period"));
        var monthlyRent = Double.valueOf(data.get("monthlyRent"));
        var paymentDueDay = Integer.parseInt(data.get("paymentDueDay"));
        var securityDeposit = Double.valueOf(data.get("securityDeposit"));

        contractBuilder.setId(UUID.randomUUID());
        contractBuilder.setPropertyId(UUID.fromString(data.get("propertyId")));
        contractBuilder.setTenantId(UUID.fromString(data.get("tenantId")));
        contractBuilder.setStartDate(startDate);
        contractBuilder.setEndDate(CalculateContractPeriod.calculate(startDate, period));
        contractBuilder.setMaintenanceClause(GenerateMaintenanceClause.generate());
        contractBuilder.setMonthlyRent(monthlyRent);
        contractBuilder.setPaymentDueDay(paymentDueDay);
        contractBuilder.setSecurityDeposit(securityDeposit);
        contractBuilder.setTerminationFee(TerminationFeePercentCalculate.calculate(period));
        contractBuilder.setStatus(ContractStatus.ACTIVE);
        contractBuilder.setContractTerms(GenerateContractTerms.generate(data.get("address"), period, startDate, CalculateContractPeriod.calculate(startDate, period), monthlyRent, paymentDueDay, securityDeposit));
    }

    @Override
    public void createAdmin(String name, String cpf, String phone) {
        userBuilder.setId(UUID.randomUUID());
        userBuilder.setName(name);
        userBuilder.setEmail(CorporateEmailCreator.create(name));
        userBuilder.setCpf(cpf);
        userBuilder.setPassword(RandomPasswordGenerator.toGenerate());
        userBuilder.setPhone(phone);
        userBuilder.setActive(true);
        userBuilder.setRole(Role.ADMIN);
    }

    @Override
    public void createTenant(String name, String cpf, String email, String phone) {
        userBuilder.setId(UUID.randomUUID());
        userBuilder.setName(name);
        userBuilder.setEmail(email);
        userBuilder.setCpf(cpf);
        userBuilder.setPassword(RandomPasswordGenerator.toGenerate());
        userBuilder.setPhone(phone);
        userBuilder.setActive(true);
        userBuilder.setRole(Role.TENANT);
    }

    @Override
    public void createOwner(String name, String cpf, String email, String phone) {
        userBuilder.setId(UUID.randomUUID());
        userBuilder.setName(name);
        userBuilder.setEmail(email);
        userBuilder.setCpf(cpf);
        userBuilder.setPassword(RandomPasswordGenerator.toGenerate());
        userBuilder.setPhone(phone);
        userBuilder.setActive(true);
        userBuilder.setRole(Role.OWNER);
    }

    @Override
    public User buildUser() {
        return userBuilder.getUser();
    }

    @Override
    public Contract buildContract() {
        return contractBuilder.build();
    }

    public void setUserBuilder(IUserBuilder userBuilder) {
        this.userBuilder = userBuilder;
    }

    public void setPropertyBuilder(IPropertyBuilder propertyBuilder) {
        this.propertyBuilder = propertyBuilder;
    }

    public void setContractBuilder(IContractBuilder contractBuilder) {
        this.contractBuilder = contractBuilder;
    }
}

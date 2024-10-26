package com.digisphere.propertize.application.director;

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

    private  IUserBuilder userBuilder;
    private  IPropertyBuilder propertyBuilder;

    private Director(IUserBuilder builder) {
        this.userBuilder = builder;
    }

    private Director(IPropertyBuilder propertyBuilder) {
        this.propertyBuilder = propertyBuilder;
    }

    public static Director createPropertyDirector() {
        return new Director(new PropertyBuilder());
    }

    public static Director createUserDirector() {
        return new Director(new UserBuilder());
    }

    @Override
    public Property buildProperty() {
        return propertyBuilder.getProperty();
    }

    @Override
    public void createProperty(String ownerId, String description, Map<String, String> address, String type, Double size, Integer bedRoomCount, Integer bathRoomCount, Boolean hasGarage, Double rentValue, String status, LocalDate constructionDate) {
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

}

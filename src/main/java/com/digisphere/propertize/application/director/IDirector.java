package com.digisphere.propertize.application.director;

import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.user.domain.User;

import java.time.LocalDate;
import java.util.Map;

public interface IDirector {
    void createAdmin(String name, String cpf, String phone);
    void createTenant(String name, String cpf, String email, String phone);
    void createOwner(String name, String cpf, String email, String phone);
    void createProperty(String ownerId, String description, Map<String, String> address, String type, Double size, Integer bedRoomCount, Integer bathRoomCount, Boolean hasGarage, Double rentValue, String status, LocalDate constructionDate);
    Property buildProperty();
    User buildUser();

}

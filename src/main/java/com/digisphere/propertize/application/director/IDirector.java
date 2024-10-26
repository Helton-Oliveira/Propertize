package com.digisphere.propertize.application.director;

import com.digisphere.propertize.application.contract.Contract;
import com.digisphere.propertize.application.contract.component.ContractStatus;
import com.digisphere.propertize.application.property.domain.Property;
import com.digisphere.propertize.application.property.domain.component.Address;
import com.digisphere.propertize.application.user.domain.User;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public interface IDirector {
    void createAdmin(String name, String cpf, String phone);
    void createTenant(String name, String cpf, String email, String phone);
    void createOwner(String name, String cpf, String email, String phone);
    void createProperty(
            String ownerId,
            String description,
            Map<String, String> address,
            String type, Double size,
            Integer bedRoomCount,
            Integer bathRoomCount,
            Boolean hasGarage,
            Double rentValue,
            String status,
            LocalDate constructionDate);
    void createContract(
            UUID propertyId,
            UUID tenantId,
            LocalDate startDate,
            Integer period,
            Double monthlyRent,
            Integer paymentDueDay,
            Double securityDeposit,
            Address address);
    Property buildProperty();
    User buildUser();
    Contract buildContract();

}

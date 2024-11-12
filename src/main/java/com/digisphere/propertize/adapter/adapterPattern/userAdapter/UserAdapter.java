package com.digisphere.propertize.adapter.adapterPattern.userAdapter;

import com.digisphere.propertize.adapter.dtos.user.CreateUserDto;
import com.digisphere.propertize.adapter.dtos.user.OutputUser;
import com.digisphere.propertize.adapter.dtos.user.UserReferenceDto;
import com.digisphere.propertize.application.user.domain.User;
import com.digisphere.propertize.application.user.useCase.interfaces.ICreateUser;
import com.digisphere.propertize.application.user.useCase.interfaces.IDisableUser;
import com.digisphere.propertize.application.user.useCase.interfaces.IGetOneUSer;
import com.digisphere.propertize.application.user.useCase.interfaces.IUpdateUser;

import java.util.HashMap;
import java.util.Map;

public class UserAdapter implements IAdapterUser {
    private final ICreateUser createUser;
    private final IUpdateUser updateUser;
    private final IGetOneUSer getOneUSer;
    private final IDisableUser disableUser;

    public UserAdapter(ICreateUser createUser, IUpdateUser updateUser, IGetOneUSer getOneUSer, IDisableUser disableUser) {
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.getOneUSer = getOneUSer;
        this.disableUser = disableUser;
    }

    @Override
    public OutputUser adaptCreateUserRequest(CreateUserDto dto) {
        Map<String, String> dataToCreate = Map.of(
                "cpf", dto.cpf(),
                "name", dto.name(),
                "email", dto.email(),
                "phone", dto.phone(),
                "role", dto.role()
        );

        User user = createUser.execute(dataToCreate);

        return new OutputUser(user.getName(), user.getEmail(), user.getCpf(), user.getPhone(), user.getRole().toString());
    }

    @Override
    public String adaptUpdateUserRequest(UserReferenceDto dto) {
        Map<String, String> field = new HashMap<>();

        if(dto.password() != null) field.put("password", dto.password());
        if(dto.phone() != null) field.put("phone", dto.phone());

        return updateUser.execute(dto.cpf(), field);
    }

    @Override
    public OutputUser adaptFindUserRequest(UserReferenceDto dto) {
        var user = getOneUSer.execute(dto.cpf());
        return new OutputUser(user.getName(), user.getEmail(), user.getCpf(), user.getPhone(), user.getRole().toString());
    }

    @Override
    public String adaptDeactivateUserRequest(UserReferenceDto dto) {
        return disableUser.execute(dto.cpf());
    }

}

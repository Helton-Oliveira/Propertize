package com.digisphere.propertize.adapter.adapterPattern.userAdapter;

import com.digisphere.propertize.adapter.dtos.user.CreateUserDto;
import com.digisphere.propertize.adapter.dtos.user.OutputUser;
import com.digisphere.propertize.adapter.dtos.user.UserReferenceDto;

public interface IAdapterUser {
    OutputUser adaptCreateUserRequest(CreateUserDto dto);
    String adaptUpdateUserRequest(UserReferenceDto dto);
    OutputUser adaptFindUserRequest(UserReferenceDto dto);
    String adaptDeactivateUserRequest(UserReferenceDto dto);
}

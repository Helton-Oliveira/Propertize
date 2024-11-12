package com.digisphere.propertize.adapter.controller;

import com.digisphere.propertize.adapter.adapterPattern.userAdapter.IAdapterUser;
import com.digisphere.propertize.adapter.dtos.user.CreateUserDto;
import com.digisphere.propertize.adapter.dtos.user.OutputUser;
import com.digisphere.propertize.adapter.dtos.user.UserReferenceDto;
import com.digisphere.propertize.application.user.useCase.interfaces.IDisableUser;
import com.digisphere.propertize.application.user.useCase.interfaces.IGetAllUsers;
import com.digisphere.propertize.application.user.useCase.interfaces.IGetOneUSer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final IAdapterUser adapter;
    private final IGetAllUsers getAllUsers;

    public UserController(IAdapterUser adapter, IGetAllUsers getAllUsers, IGetOneUSer getOneUSer, IDisableUser disableUser) {
        this.adapter = adapter;
        this.getAllUsers = getAllUsers;

    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDto dto, UriComponentsBuilder uriBuilder) {
      var user = adapter.adaptCreateUserRequest(dto);

      var uri = uriBuilder.path("/users/{cpf}").buildAndExpand(user.cpf()).toUri();
      return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<List<OutputUser>> getAll() {
        List<OutputUser> outputUsers = getAllUsers.execute().stream()
                .map(u -> {
                    return new OutputUser(u.getName(), u.getEmail(), u.getCpf(), u.getPhone(), u.getRole().toString());
                })
                .toList();

        return ResponseEntity.ok().body(outputUsers);
    }

    @GetMapping("/one")
    public ResponseEntity<OutputUser> getOne(@RequestBody @Valid UserReferenceDto dto) {
        OutputUser outputUser = adapter.adaptFindUserRequest(dto);

        return ResponseEntity.ok(outputUser);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> updateUser(@RequestBody @Valid UserReferenceDto dto) {
        String response = adapter.adaptUpdateUserRequest(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<String> disableUser(@RequestBody @Valid UserReferenceDto dto) {
        String response = adapter.adaptDeactivateUserRequest(dto);
        return ResponseEntity.ok(response);
    }
}

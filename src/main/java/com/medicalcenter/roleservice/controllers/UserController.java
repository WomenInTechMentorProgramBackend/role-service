package com.medicalcenter.roleservice.controllers;

import com.medicalcenter.roleservice.facades.UserFacade;
import io.tej.SwaggerCodgen.api.UsersApi;
import io.tej.SwaggerCodgen.model.UserDto;
import io.tej.SwaggerCodgen.model.UsersModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/role-service/v1")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@Transactional
public class UserController implements UsersApi {

    private final UserFacade userFacade;

    @Override
    public ResponseEntity<UserDto> addRoleToUser(String userId, String roleId) {
        var userDto = userFacade.addRoleToUser(userId, roleId);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<UserDto> addUser(String userId, String roleId) {
        var userDto = userFacade.addUser(userId, roleId);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteUserById(String userId) {
        userFacade.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<UserDto> deleteUserRole(String userId, String roleId) {
        var userDto = userFacade.deleteUserRole(userId, roleId);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<UsersModel> getAllUsers() {
        var usersModel = userFacade.getAllUsers();
        return new ResponseEntity<>(usersModel, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String userId) {
        var userDto = userFacade.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }
}

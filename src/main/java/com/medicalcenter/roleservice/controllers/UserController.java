package com.medicalcenter.roleservice.controllers;

import com.medicalcenter.roleservice.entity.User;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.service.impl.RoleServiceImpl;
import com.medicalcenter.roleservice.service.impl.UserServiceImpl;
import io.tej.SwaggerCodgen.api.UsersApi;
import io.tej.SwaggerCodgen.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<UserDto> addRoleToUser(String userId, String roleId) {
        var user = userService.getUserById(UUID.fromString(userId)).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var userRoles = user.getRoles();
        if (userRoles.contains(role)) {
            throw new ObjectAlreadyExistException("User with id " + userId + " already has a role with id " + roleId);
        } else {
            userRoles.add(role);
        }
        user = userService.saveUser(user);
        var userDto = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<UserDto> addUser(String userId, String roleId) {
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var user = User.builder()
                .roles(List.of(role))
                .createdBy("admin")
                .createdAt(LocalDateTime.now())
                .build();
        user = userService.saveUser(user);
        var userDto = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteUserById(String userId) {
        userService.deleteUserByID(UUID.fromString(userId));
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<UserDto> deleteUserRole(String userId, String roleId) {
        var user = userService.getUserById(UUID.fromString(userId)).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var userRoles = user.getRoles();
        if (userRoles.contains(role)) {
            userRoles.remove(role);
        } else {
            throw new ResourceNotFoundException("User with id " + userId + "does not have role with ID " + roleId);
        }
        user = userService.updateUser(UUID.fromString(userId), user);
        var userDto = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        var users = userService.getAllUsers();
        var dtoUsers = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoUsers, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String userId) {
        var user = userService.getUserById(UUID.fromString(userId)).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        var userDto = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }
}

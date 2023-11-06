package com.medicalcenter.roleservice.facades;

import com.medicalcenter.roleservice.entity.User;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.mapper.UserDtoUserMapper;
import com.medicalcenter.roleservice.service.impl.RoleServiceImpl;
import com.medicalcenter.roleservice.service.impl.UserServiceImpl;
import io.tej.SwaggerCodgen.model.UserDto;
import io.tej.SwaggerCodgen.model.UsersModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserFacade {
    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;
    private final UserDtoUserMapper userMapper;

    public UserDto addRoleToUser(String userId, String roleId) {
        var user = userService.getUserById(UUID.fromString(userId)).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var userRoles = user.getRoles();
        if (userRoles.contains(role)) {
            throw new ObjectAlreadyExistException("User with id " + userId + " already has a role with id " + roleId);
        } else {
            userRoles.add(role);
        }
        user = userService.updateUser(user.getId(), user);
        return userMapper.userToUserDto(user);
    }

    public UserDto addUser(String userId, String roleId) {
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var user = User.builder()
                .roles(List.of(role))
                .externalId(UUID.fromString(userId))
                .createdBy("admin")
                .createdAt(LocalDateTime.now())
                .build();
        user = userService.saveUser(user);
        return userMapper.userToUserDto(user);
    }

    public void deleteUserById(String userId) {
        userService.deleteUserByID(UUID.fromString(userId));
    }

    public UserDto deleteUserRole(String userId, String roleId) {
        var user = userService.getUserById(UUID.fromString(userId)).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var userRoles = user.getRoles();
        if (userRoles.contains(role) && userRoles.size() > 1) {
            userRoles.remove(role);
        } else {
            throw userRoles.size() > 1 ?
                    new ResourceNotFoundException("User with id " + userId + "does not have role with ID " + roleId) :
                    new RuntimeException("The numbers of roles cannot be less then 1");
        }
        user = userService.updateUser(UUID.fromString(userId), user);
        return userMapper.userToUserDto(user);
    }

    public UsersModel getAllUsers() {
        var users = userService.getAllUsers();
        return new UsersModel()
                .users(users.stream()
                        .map(userMapper::userToUserDto)
                        .toList());
    }

    public UserDto getUserById(String userId) {
        var user = userService.getUserById(UUID.fromString(userId)).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
        return userMapper.userToUserDto(user);
    }
}

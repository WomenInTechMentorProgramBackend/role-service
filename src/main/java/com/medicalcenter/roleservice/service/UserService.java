package com.medicalcenter.roleservice.service;

import com.medicalcenter.roleservice.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(UUID id);

    User saveUser(User user);

    User updateUser(UUID id, User updatedUser);

    String deleteUserByID(UUID id);
}

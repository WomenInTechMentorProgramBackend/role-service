package com.medicalcenter.roleservice.service.impl;

import com.medicalcenter.roleservice.entity.User;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.UserRepository;
import com.medicalcenter.roleservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id);
        } else {
            throw new ResourceNotFoundException("User not fount with id: " + id);        }
    }

    @Override
    public User saveUser(User user) {
        var saveUser = userRepository.findByExternalId(user.getExternalId());
        if (saveUser.isPresent()) {
            throw new ObjectAlreadyExistException("User already exists with externalId: " + user.getExternalId());
        }
            return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, User updatedUser) {
        var existingUserOptional = userRepository.findById(id);
        if(existingUserOptional.isPresent()) {
            var existingUser = existingUserOptional.get();
            existingUser.setExternalId(updatedUser.getExternalId());
            existingUser.setCreatedAt(updatedUser.getCreatedAt());
            existingUser.setUpdatedAt(updatedUser.getUpdatedAt());
            existingUser.setCreatedBy(updatedUser.getCreatedBy());
            existingUser.setUpdatedBy(updatedUser.getUpdatedBy());
            return userRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException("User not found with id: " +id);
        }
    }

    @Override
    public String deleteUserByID(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User(" + id + ")" + " has been deleted!";
        } else {
            throw new ResourceNotFoundException("User not found with id: " +id);
        }
    }
}

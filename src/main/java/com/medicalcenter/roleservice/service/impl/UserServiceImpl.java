package com.medicalcenter.roleservice.service.impl;

import com.medicalcenter.roleservice.entity.User;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.UserRepository;
import com.medicalcenter.roleservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if(existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
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

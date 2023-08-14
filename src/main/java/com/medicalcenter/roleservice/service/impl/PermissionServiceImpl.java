package com.medicalcenter.roleservice.service.impl;

import com.medicalcenter.roleservice.entity.Permission;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.PermissionRepository;
import com.medicalcenter.roleservice.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> getPermissionById(UUID id) {
        if (permissionRepository.existsById(id)) {
            return permissionRepository.findById(id);
        } else {
            throw new ResourceNotFoundException("Permission not found with id: " + id);
        }
    }

    @Override
    public Permission savePermission(Permission permission) {
        var savedPermission = permissionRepository.findByName(permission.getName());
        if (savedPermission.isPresent()) {
            throw new ObjectAlreadyExistException("Permission already exists with name: " + permission.getName());
        }
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(UUID id, Permission updatedPermission) {
        var existingPermissionOptional = permissionRepository.findById(id);
        if (existingPermissionOptional.isPresent()) {
            var existingPermission = existingPermissionOptional.get();
            existingPermission.setName(updatedPermission.getName());
            existingPermission.setDescription(updatedPermission.getDescription());
            existingPermission.setIsActive(updatedPermission.getIsActive());
            existingPermission.setCreatedAt(updatedPermission.getCreatedAt());
            existingPermission.setCreatedBy(updatedPermission.getCreatedBy());
            existingPermission.setUpdatedAt(updatedPermission.getUpdatedAt());
            existingPermission.setUpdatedBy(updatedPermission.getUpdatedBy());
            return permissionRepository.save(existingPermission);
        } else {
            throw new ResourceNotFoundException("Permission not found with id: " + id);
        }
    }

    @Override
    public String deletePermissionById(UUID id) {
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
            return "Permission(" + id + ")" + " has been deleted!";
        } else {
            throw new ResourceNotFoundException("Permission not found with id: " + id);
        }
    }
}

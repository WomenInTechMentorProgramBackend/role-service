package com.medicalcenter.roleservice.service;

import com.medicalcenter.roleservice.entity.Permission;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionService {
    List<Permission> getAllPermissions();

    Optional<Permission> getPermissionById(UUID id);

    Permission savePermission(Permission permission);

    Permission updatePermission(UUID id, Permission updatedPermission);

    String deletePermissionById(UUID id);





}

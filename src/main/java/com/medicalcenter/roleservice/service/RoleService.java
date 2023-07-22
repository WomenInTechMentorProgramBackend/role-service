package com.medicalcenter.roleservice.service;

import com.medicalcenter.roleservice.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    List<Role> getAllRoles();

    Optional<Role> getRoleById(UUID id);

    Role saveRole(Role role);

    Role updateRole(UUID id, Role role);

    String deleteRoleById(UUID id);
}

package com.medicalcenter.roleservice.service.impl;

import com.medicalcenter.roleservice.entity.Role;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.RoleRepository;
import com.medicalcenter.roleservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(UUID id) {
        if (roleRepository.existsById(id)) {
            return roleRepository.findById(id);
        } else {
            throw new ResourceNotFoundException("Role not found with id: " + id);
        }
    }

    @Override
    public Role saveRole(Role role) {
        Optional<Role> savedRole = roleRepository.findByName(role.getName());
        if (savedRole.isPresent()) {
            throw new ObjectAlreadyExistException("Role already exists with name: " + role.getName());
        }
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(UUID id, Role updatedRole) {
        Optional<Role> existingRoleOptional = roleRepository.findById(id);
        if (existingRoleOptional.isPresent()) {
            Role existingRole = existingRoleOptional.get();
            existingRole.setName(updatedRole.getName());
            existingRole.setDescription(updatedRole.getDescription());
            existingRole.setIsActive(updatedRole.getIsActive());
            existingRole.setUpdatedAt(updatedRole.getUpdatedAt());
            existingRole.setUpdatedBy(updatedRole.getUpdatedBy());
            return roleRepository.save(existingRole);
        } else {
            throw new ResourceNotFoundException("Role not found with id: " + id);
        }
    }

    @Override
    public String deleteRoleById(UUID id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return "Role(" + id + ")" + " has been deleted!";
        } else {
            throw new ResourceNotFoundException("Role not found with id: " + id);
        }
    }
}

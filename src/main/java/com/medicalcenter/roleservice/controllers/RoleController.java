package com.medicalcenter.roleservice.controllers;

import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.mapper.PermissionMapper;
import com.medicalcenter.roleservice.mapper.RoleMapper;
import com.medicalcenter.roleservice.service.impl.RoleServiceImpl;
import io.tej.SwaggerCodgen.api.RolesApi;
import io.tej.SwaggerCodgen.model.PermissionDto;
import io.tej.SwaggerCodgen.model.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoleController implements RolesApi {
    private final RoleServiceImpl roleService;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    @Override
    public ResponseEntity<RoleDto> addRole(RoleDto roleDto) {
        var role = roleMapper.dtoToEntity(roleDto);
        role.setCreatedAt(LocalDateTime.now());
        role.setCreatedBy("admin");
        role = roleService.saveRole(role);
        roleDto = roleMapper.entityToDto(role);
        return new ResponseEntity<>(roleDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteRoleById(String roleId) {
        roleService.deleteRoleById(UUID.fromString(roleId));
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<List<PermissionDto>> getAllRolePermissionsByRoleId(String roleId) {
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var dtoPermissions = role.getPermissions().stream()
                .map(permissionMapper::entityToDto)
                .toList();
        return new ResponseEntity<>(dtoPermissions, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        var roles = roleService.getAllRoles();
        var dtoRoles = roles.stream()
                .map(roleMapper::entityToDto)
                .toList();
        return new ResponseEntity<>(dtoRoles, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<RoleDto> getRoleById(String roleId) {
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var roleDto = roleMapper.entityToDto(role);
        return new ResponseEntity<>(roleDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<RoleDto> updateRole(String roleId, RoleDto roleDto) {
        var role = roleMapper.dtoToEntity(roleDto);
        role = roleService.updateRole(UUID.fromString(roleId), role);
        roleDto = roleMapper.entityToDto(role);
        return new ResponseEntity<>(roleDto, HttpStatusCode.valueOf(200));
    }
}

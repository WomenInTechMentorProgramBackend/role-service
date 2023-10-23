package com.medicalcenter.roleservice.facades;

import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.mapper.PermissionDtoPermissionMapper;
import com.medicalcenter.roleservice.mapper.RoleDtoRoleMapper;
import com.medicalcenter.roleservice.service.impl.RoleServiceImpl;
import io.tej.SwaggerCodgen.model.PermissionsModel;
import io.tej.SwaggerCodgen.model.RoleDto;
import io.tej.SwaggerCodgen.model.RolesModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class RoleFacade {
    private final RoleServiceImpl roleService;

    private final RoleDtoRoleMapper roleMapper;

    private final PermissionDtoPermissionMapper permissionMapper;

    public RoleDto addRole(RoleDto roleDto) {
        var role = roleMapper.roleDtoToRole(roleDto);
        role.setCreatedAt(LocalDateTime.now());
        role.setCreatedBy("admin");
        role = roleService.saveRole(role);
        return roleMapper.roleToRoleDto(role);
    }

    public void deleteRoleById(String roleId) {
        roleService.deleteRoleById(UUID.fromString(roleId));
    }

    public PermissionsModel getAllRolePermissionsByRoleId(String roleId) {
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        return new PermissionsModel()
                .permissions(role.getPermissions()
                        .stream()
                        .map(permissionMapper::permissionToPermissionDto)
                        .toList());
    }

    public RolesModel getAllRoles() {
        var roles = roleService.getAllRoles();
       return new RolesModel()
                .roles(roles.stream()
                        .map(roleMapper::roleToRoleDto)
                        .toList());
    }

    public RoleDto getRoleById(String roleId) {
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        return roleMapper.roleToRoleDto(role);
    }

    public RoleDto updateRole(String roleId, RoleDto roleDto) {
        var role = roleMapper.roleDtoToRole(roleDto);
        role = roleService.updateRole(UUID.fromString(roleId), role);
        return roleMapper.roleToRoleDto(role);
    }

}

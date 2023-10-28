package com.medicalcenter.roleservice.facades;

import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.mapper.PermissionDtoPermissionMapper;
import com.medicalcenter.roleservice.service.PermissionService;
import com.medicalcenter.roleservice.service.RoleService;
import io.tej.SwaggerCodgen.model.PermissionDto;
import io.tej.SwaggerCodgen.model.PermissionsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class PermissionFacade {
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final PermissionDtoPermissionMapper permissionMapper;

    public PermissionDto addPermission(String roleId, PermissionDto permissionDto) {
        var role = roleService.getRoleById(UUID.fromString(roleId)).orElseThrow(() -> new ResourceNotFoundException("Role with ID " + roleId + " not found"));
        var permission = permissionMapper.permissionDtoToPermission(permissionDto);
        permission.setCreatedAt(LocalDateTime.now());
        permission.setCreatedBy("admin");
        permission = permissionService.savePermission(permission);
        role.getPermissions().add(permission);
        roleService.updateRole(UUID.fromString(roleId), role);
        return permissionMapper.permissionToPermissionDto(permission);
    }

    public void deletePermissionById(String permissionId) {
        permissionService.deletePermissionById(UUID.fromString(permissionId));
    }

    public PermissionsModel getAllPermissions() {
        return new PermissionsModel().permissions(
                Stream.ofNullable(permissionService.getAllPermissions())
                        .flatMap(Collection::stream)
                        .map(permissionMapper::permissionToPermissionDto)
                        .collect(Collectors.toList()));
    }

    public PermissionDto getPermissionById(String permissionId) {
        var permission =  permissionService.getPermissionById(UUID.fromString(permissionId)).orElseThrow(() -> new ResourceNotFoundException("Permission with ID " + permissionId + " not found"));
        return permissionMapper.permissionToPermissionDto(permission);
    }

    public PermissionDto updatePermission(String permissionId, PermissionDto permissionDto) {
        var permission = permissionMapper.permissionDtoToPermission(permissionDto);
        permission = permissionService.updatePermission(UUID.fromString(permissionId), permission);
        return permissionMapper.permissionToPermissionDto(permission);
    }
}

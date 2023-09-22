package com.medicalcenter.roleservice.controllers;

import com.medicalcenter.roleservice.facades.RoleFacade;
import io.tej.SwaggerCodgen.api.RolesApi;
import io.tej.SwaggerCodgen.model.PermissionsModel;
import io.tej.SwaggerCodgen.model.RoleDto;
import io.tej.SwaggerCodgen.model.RolesModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@Transactional
public class RoleController implements RolesApi {
    private final RoleFacade roleFacade;

    @Override
    public ResponseEntity<RoleDto> addRole(RoleDto roleDto) {
        roleDto = roleFacade.addRole(roleDto);
        return new ResponseEntity<>(roleDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deleteRoleById(String roleId) {
        roleFacade.deleteRoleById(roleId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<PermissionsModel> getAllRolePermissionsByRoleId(String roleId) {
        var permissionsModel = roleFacade.getAllRolePermissionsByRoleId(roleId);
        return new ResponseEntity<>(permissionsModel, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<RolesModel> getAllRoles() {
        var rolesModel = roleFacade.getAllRoles();
        return new ResponseEntity<>(rolesModel, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<RoleDto> getRoleById(String roleId) {
        var roleDto = roleFacade.getRoleById(roleId);
        return new ResponseEntity<>(roleDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<RoleDto> updateRole(String roleId, RoleDto roleDto) {
        roleDto = roleFacade.updateRole(roleId, roleDto);
        return new ResponseEntity<>(roleDto, HttpStatusCode.valueOf(200));
    }
}

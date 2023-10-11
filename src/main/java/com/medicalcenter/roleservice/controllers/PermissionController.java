package com.medicalcenter.roleservice.controllers;

import com.medicalcenter.roleservice.facades.PermissionFacade;
import io.tej.SwaggerCodgen.api.PermissionsApi;
import io.tej.SwaggerCodgen.model.PermissionDto;
import io.tej.SwaggerCodgen.model.PermissionsModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role-service/v1")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@Transactional
public class PermissionController implements PermissionsApi {
    private final PermissionFacade permissionFacade;

    @Override
    public ResponseEntity<PermissionDto> addPermission(String roleId, PermissionDto permissionDto) {
        permissionDto = permissionFacade.addPermission(roleId, permissionDto);
        return new ResponseEntity<>(permissionDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Void> deletePermissionById(String permissionId) {
        permissionFacade.deletePermissionById(permissionId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<PermissionsModel> getAllPermissions() {
        var permissionModel = permissionFacade.getAllPermissions();
        return new ResponseEntity<>(permissionModel, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<PermissionDto> getPermissionById(String permissionId) {
        var permissionDto = permissionFacade.getPermissionById(permissionId);
        return new ResponseEntity<>(permissionDto, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<PermissionDto> updatePermission(String permissionId, PermissionDto permissionDto) {
        permissionDto = permissionFacade.updatePermission(permissionId, permissionDto);
        return new ResponseEntity<>(permissionDto, HttpStatusCode.valueOf(200));
    }
}

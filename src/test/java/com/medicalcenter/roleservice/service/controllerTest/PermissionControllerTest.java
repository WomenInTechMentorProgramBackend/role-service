package com.medicalcenter.roleservice.service.controllerTest;

import com.medicalcenter.roleservice.controllers.PermissionController;
import com.medicalcenter.roleservice.controllers.RoleController;
import com.medicalcenter.roleservice.entity.Permission;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.PermissionRepository;
import com.medicalcenter.roleservice.repository.RoleRepository;
import com.medicalcenter.roleservice.service.BaseTest;
import io.tej.SwaggerCodgen.model.PermissionDto;
import io.tej.SwaggerCodgen.model.RoleDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PermissionControllerTest extends BaseTest {
    @Autowired
    private PermissionController permissionController;
    @Autowired
    private RoleController roleController;
    private Permission entity;
    private PermissionDto dto;
    private RoleDto roleDto;
    @BeforeEach
    void clearDatabase(@Autowired RoleRepository roleRepository, @Autowired PermissionRepository permissionRepository) {
        roleRepository.deleteAll();
        permissionRepository.deleteAll();
    }
    @BeforeEach
    public void setUp() {
        entity = Permission.builder()
                .id(UUID.randomUUID())
                .name("dsmth")
                .description("something")
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 1")
                .isActive(true)
                .build();
        dto = new PermissionDto()
                .name("dsmth")
                .description("something")
                .createdAt(OffsetDateTime.now())
                .createdBy("Test User 1")
                .isActive(true);
        roleDto = new RoleDto()
                .name("doctor")
                .description("something")
                .createdAt(OffsetDateTime.now())
                .createdBy("Test User 1")
                .isActive(true);
    }

    @DisplayName("JUnit test for addPermission method")
    @Test
    void addPermission_NewPermission_ShouldReturnPermissionDto() {
        roleDto = roleController.addRole(roleDto).getBody();
        var result = permissionController.addPermission(roleDto.getId(), dto);
        assertNotNull(result);
        assertNotNull(result.getBody().getId());
        assertEquals(roleController.getRoleById(roleDto.getId()).getBody().getPermissions().get(0).getId(), result.getBody().getId());
    }
    @DisplayName("JUnit test for deletePermissionById method")
    @Test
    void deletePermissionById_void_ShouldDeletePermissionFromService() {
        var savedRoleDto = roleController.addRole(roleDto).getBody();
        dto = permissionController.addPermission(savedRoleDto.getId(), dto).getBody();
        savedRoleDto.setPermissions(List.of(dto));
        roleController.updateRole(savedRoleDto.getId(), savedRoleDto);
        assertEquals(1, roleController.getRoleById(savedRoleDto.getId()).getBody().getPermissions().size());
        permissionController.deletePermissionById(dto.getId());
        try {
            permissionController.getPermissionById(dto.getId());
            assert false;
        } catch (ResourceNotFoundException ignored){
            var permissions = roleController.getRoleById(savedRoleDto.getId()).getBody().getPermissions();
            if(!isEmpty(permissions)){
                assert false;
            }
        }
    }

    @DisplayName("JUnit test for getAllPermissions method")
    @Test
    void getAllPermissions_PermissionsModel_ShouldReturnAllPermissionsFromService() {
        roleDto = roleController.addRole(roleDto).getBody();
        permissionController.addPermission(roleDto.getId(), dto);
        var dto1 = new PermissionDto()
                .name("dsmth1")
                .description("something1")
                .createdAt(OffsetDateTime.now())
                .createdBy("Test User 2")
                .isActive(true);
        permissionController.addPermission(roleDto.getId(), dto1);
        var dto2 = new PermissionDto()
                .name("dsmth2")
                .description("something2")
                .createdAt(OffsetDateTime.now())
                .createdBy("Test User 3")
                .isActive(true);
        var roleDto2 = new RoleDto()
                .name("doctor2")
                .description("something2")
                .createdAt(OffsetDateTime.now())
                .createdBy("Test User 2")
                .isActive(true);
        roleDto2 = roleController.addRole(roleDto2).getBody();
        permissionController.addPermission(roleDto2.getId(), dto2);
        var result = permissionController.getAllPermissions();
        assertEquals(3, result.getBody().getPermissions().size());
    }
    @DisplayName("JUnit test for getPermissionById method")
    @Test
    void getPermissionById_PermissionDto_ShouldReturnPermissionDto() {
        roleDto = roleController.addRole(roleDto).getBody();
        dto = permissionController.addPermission(roleDto.getId(), dto).getBody();
        var permission = permissionController.getPermissionById(dto.getId()).getBody();
        assertEquals(dto.getId(), permission.getId());
    }
    @DisplayName("JUnit test for updatePermission method")
    @Test
    void updatePermission_UpdatedPermission_ShouldReturnPermissionDto() {
        roleDto = roleController.addRole(roleDto).getBody();
        dto = permissionController.addPermission(roleDto.getId(), dto).getBody();
        dto.setDescription("param-pam-pam");
        var result = permissionController.updatePermission(dto.getId(), dto);
        assertEquals("param-pam-pam", result.getBody().getDescription());
    }
}

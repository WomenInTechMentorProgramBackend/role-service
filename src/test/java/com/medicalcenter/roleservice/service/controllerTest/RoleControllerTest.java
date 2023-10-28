package com.medicalcenter.roleservice.service.controllerTest;

import com.medicalcenter.roleservice.controllers.RoleController;
import com.medicalcenter.roleservice.controllers.UserController;
import com.medicalcenter.roleservice.entity.Permission;
import com.medicalcenter.roleservice.entity.Role;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.RoleRepository;
import com.medicalcenter.roleservice.service.BaseTest;
import io.tej.SwaggerCodgen.model.RoleDto;
import io.tej.SwaggerCodgen.model.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoleControllerTest extends BaseTest {
    @Autowired
    private RoleController roleController;
    @Autowired
    private UserController userController;


    private Role entity;
    private RoleDto dto;
    @BeforeEach
    void clearDatabase(@Autowired RoleRepository roleRepository) {
        roleRepository.deleteAll();
    }
    @BeforeEach
    public void setUp() {
        entity = Role.builder()
                .id(UUID.randomUUID())
                .name("doctor")
                .description("something")
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 1")
                .isActive(true)
                .permissions(List.of
                        (Permission.builder()
                                .name("smth")
                                .description("smth about")
                                .createdBy("Test User 1")
                                .createdAt(LocalDateTime.now())
                                .isActive(true)
                                .build()))
                .build();
        dto = new RoleDto()
                .name("doctor")
                .description("something")
                .createdAt(OffsetDateTime.now())
                .createdBy("Test User 1")
                .isActive(true);
//                .permissions(List.of
//                        (new PermissionDto()
//                                .name("smth")
//                                .description("smth about")
//                                .createdBy("Test User 1")
//                                .createdAt(OffsetDateTime.now())
//                                .isActive(true)));
    }
    @DisplayName("JUnit test for addRole method")
    @Test
    void addRole_NewRole_ShouldReturnRoleDto() {
        var result = roleController.addRole(dto);
        assertNotNull(result);
        assertNotNull(result.getBody().getId());
        assertEquals(entity.getName(), result.getBody().getName());
    }

    @DisplayName("JUnit test for deleteRoleById method")
    @Test
    void deleteRoleById_void_ShouldDeleteRoleFromService() {
        var savedDto = roleController.addRole(dto).getBody();
        var dtoId = savedDto.getId();
        assertNotNull(roleController.getRoleById(dtoId));
        var userDto = userController.addUser(UUID.randomUUID().toString(), dtoId).getBody();
        assertEquals(1, userDto.getRoles().size());
        roleController.deleteRoleById(dtoId);
        userDto = userController.getUserById(userDto.getId()).getBody();
        assertEquals(0,  userDto.getRoles().size());
        try {
            roleController.getRoleById(dtoId);
            assert false;
        } catch (ResourceNotFoundException ignored){
        }
    }

    @DisplayName("JUnit test for getAllRoles method")
    @Test
    void getAllRoles_RolesModel_ShouldReturnAllRolesFromService() {
        roleController.addRole(dto);
        roleController.addRole(dto.name("doctor1").description("smth1"));
        roleController.addRole(dto.name("doctor2").description("smth2"));
        var result = roleController.getAllRoles();
        assertEquals(3, result.getBody().getRoles().size());
    }

    @DisplayName("JUnit test for getRoleById method")
    @Test
    void getRoleById_RoleDto_ShouldReturnRoleDto() {
        var role = roleController.addRole(dto);
        var role1 = roleController.getRoleById(role.getBody().getId());
        assertEquals(role.getBody().getName(), role1.getBody().getName());
    }
    @DisplayName("JUnit test for updateRole method")
    @Test
    void updateRole_UpdatedRole_ShouldReturnRoleDto() {
        var role = roleController.addRole(dto);
        var result = roleController.updateRole(role.getBody().getId(), role.getBody().name("admin"));
        assertEquals("admin", result.getBody().getName());
    }
}

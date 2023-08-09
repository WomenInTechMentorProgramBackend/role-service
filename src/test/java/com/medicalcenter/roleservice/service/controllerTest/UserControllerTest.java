package com.medicalcenter.roleservice.service.controllerTest;

import com.medicalcenter.roleservice.controllers.RoleController;
import com.medicalcenter.roleservice.controllers.UserController;
import com.medicalcenter.roleservice.entity.Role;
import com.medicalcenter.roleservice.entity.User;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.UserRepository;
import com.medicalcenter.roleservice.service.BaseTest;
import io.tej.SwaggerCodgen.model.RoleDto;
import io.tej.SwaggerCodgen.model.UserDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest extends BaseTest {
    @Autowired
    private UserController userController;
    @Autowired
    private RoleController roleController;

    private User entity;
    private UserDto dto;
    private RoleDto roleDto;
    @AfterEach
    void clearDatabase(@Autowired UserRepository userRepository) {
        userRepository.deleteAll();
    }
    @BeforeAll
    public void setUp() {
        entity = User.builder()
                .id(UUID.randomUUID())
                .externalId(UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 1")
                .roles(List.of
                        (Role.builder()
                                .name("smth")
                                .description("smth about")
                                .createdBy("Test User 1")
                                .createdAt(LocalDateTime.now())
                                .isActive(true)
                                .build()))
                .build();
        dto = new UserDto()
                .externalId(UUID.randomUUID().toString())
                .createdAt(OffsetDateTime.now())
                .createdBy("Test User 1");
//                .roles(List.of
//                        (new RoleDto()
//                                .name("smth")
//                                .description("smth about")
//                                .createdBy("Test User 1")
//                                .createdAt(OffsetDateTime.now())
//                                .isActive(true)));

        roleDto = new RoleDto()
                            .name("doctor")
                            .description("smth")
                            .isActive(true);
        roleDto = roleController.addRole(roleDto).getBody();
    }
    @DisplayName("JUnit test for addUser method")
    @Test
    void addUser_NewUser_ShouldReturnUserDto() {
        var result = userController.addUser(dto.getExternalId(), roleDto.getId());
        assertNotNull(result);
        assertEquals(roleDto.getId(), result.getBody().getRoles().get(0).getId());
    }

    @DisplayName("JUnit test for deleteUserById method")
    @Test
    void deleteUserById_void_ShouldDeleteUserFromService() {
        var savedDto = userController.addUser(dto.getExternalId(), roleDto.getId());
        var dtoId = savedDto.getBody().getId();
        assertNotNull(userController.getUserById(dtoId));
        userController.deleteUserById(dtoId);
        try {
            userController.getUserById(dtoId);
            assert false;
        } catch (ResourceNotFoundException ignored){}
    }
    @DisplayName("JUnit test for deleteUserRole method")
    @Test
    void deleteUserRole_void_ShouldDeleteRoleFromUser() {
        var savedDto = userController.addUser(dto.getExternalId(), roleDto.getId());
        var dtoId = savedDto.getBody().getId();
        savedDto = userController.deleteUserRole(dtoId, roleDto.getId());
        assertEquals(savedDto.getBody().getRoles().size(), 0);
    }

    @DisplayName("JUnit test for getAllUsers method")
    @Test
    void getAllUsers_UsersModel_ShouldReturnAllUsersFromService() {
        userController.addUser(UUID.randomUUID().toString(), roleDto.getId());
        userController.addUser(UUID.randomUUID().toString(), roleDto.getId());
        userController.addUser(UUID.randomUUID().toString(), roleDto.getId());
        var result = userController.getAllUsers();
        assertEquals(3, result.getBody().getUsers().size());
    }

    @DisplayName("JUnit test for getUserById method")
    @Test
    void getUserById_UserDto_ShouldReturnUserDto() {
        var user = userController.addUser(UUID.randomUUID().toString(), roleDto.getId());
        var user1 = userController.getUserById(user.getBody().getId());
        assertEquals(user.getBody(), user1.getBody());
    }

}

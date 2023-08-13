package com.medicalcenter.roleservice.service.serviceTest;

import com.medicalcenter.roleservice.entity.Role;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.RoleRepository;
import com.medicalcenter.roleservice.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    private Role role;

    @BeforeEach
    public void setUp() {
        role = Role.builder()
                .id(UUID.randomUUID())
                .name("Role 1")
                .description("Description 1")
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 1")
                .build();
    }

    @DisplayName("JUnit test for getAllRoles method")
    @Test
    void getAllRoles_ShouldReturnListOfRoles() {
        var role1 = Role.builder()
                .id(UUID.randomUUID())
                .name("Role 2")
                .description("Description 2")
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 2")
                .build();

        when(roleRepository.findAll()).thenReturn(List.of(role,role1));

        var roleList = roleService.getAllRoles();

        assertEquals(2, roleList.size());
        verify(roleRepository, times(1)).findAll();
    }

    @DisplayName("JUnit test for getRoleById method")
    @Test
    void getRoleById_ExistingId_ShouldReturnRoleObject() {
        var roleId = role.getId();
        when(roleRepository.existsById(roleId)).thenReturn(true);
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        var result = roleService.getRoleById(roleId);

        assertTrue(result.isPresent());
        assertEquals(role, result.get());
        verify(roleRepository, times(1)).existsById(roleId);
        verify(roleRepository, times(1)).findById(roleId);
    }

    @DisplayName("JUnit test for getRoleById method throws ResourceNotFoundException")
    @Test
    void getRoleById_NotExistingID_ShouldThrowResourceNotFoundException() {
        var roleId = UUID.randomUUID();

        when(roleRepository.existsById(roleId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> roleService.getRoleById(roleId));
        verify(roleRepository, times(1)).existsById(roleId);
        verify(roleRepository, never()).findById(roleId);
    }

    @DisplayName("JUnit test for saveRole method")
    @Test
    void saveRole_NewRole_ShouldReturnRoleObject() {
        when(roleRepository.findByName(role.getName())).thenReturn(Optional.empty());

        when(roleRepository.save(role)).thenReturn(role);

        var result = roleService.saveRole(role);

        assertNotNull(result);
        assertEquals(role, result);

        verify(roleRepository, times(1)).findByName(role.getName());
        verify(roleRepository, times(1)).save(role);
    }

    @DisplayName("JUnit test for saveRole method which throws ObjectAlreadyExistException")
    @Test
    void  saveRole_ExistingName_ShouldThrowObjectAlreadyExistException() {
        when(roleRepository.findByName(role.getName())).thenReturn(Optional.of(role));

        assertThrows(ObjectAlreadyExistException.class, () -> roleService.saveRole(role));

        verify(roleRepository, times(1)).findByName(role.getName());
        verify(roleRepository, never()).save(role);
    }

    @DisplayName("JUnit test for updateRole method")
    @Test
    void updateRole_ExistingId_ShouldReturnUpdatedRole() {
        var roleId = role.getId();

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        when(roleRepository.save(role)).thenReturn(role);

        role.setName("Updated Role");
        role.setDescription("Updated Description");
        role.setIsActive(false);
        role.setCreatedAt(LocalDateTime.now());
        role.setCreatedBy("Updated User");

        var updatedRole = roleService.updateRole(roleId, role);

        assertNotNull(updatedRole);
        assertEquals(role.getName(), updatedRole.getName());
        assertEquals(role.getDescription(), updatedRole.getDescription());
        assertEquals(role.getIsActive(), updatedRole.getIsActive());
        verify(roleRepository, times(1)).findById(roleId);
        verify(roleRepository, times(1)).save(role);
    }

    @DisplayName("JUnit test for updateRole method throws ResourceNotFoundException")
    @Test
    void updateRole_NotExistingId_ShouldThrowResourceNotFoundException() {
        var roleId = UUID.randomUUID();
        var updatedRole = new Role();
        updatedRole.setName("Updated Role");
        updatedRole.setDescription("Updated Description");
        updatedRole.setIsActive(false);
        updatedRole.setCreatedAt(LocalDateTime.now());
        updatedRole.setCreatedBy("Updated User");

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> roleService.updateRole(roleId, updatedRole));
        verify(roleRepository, times(1)).findById(roleId);
        verify(roleRepository, never()).save(any(Role.class));
    }

    @DisplayName("JUnit test for deleteRoleById method")
    @Test
    void deleteRoleById_ExistingId_ShouldReturnDeletedMessage() {
        var roleId = role.getId();
        when(roleRepository.existsById(roleId)).thenReturn(true);

        var result = roleService.deleteRoleById(roleId);

        assertNotNull(result);
        assertEquals("Role(" + roleId + ")" +  " has been deleted!", result);
        verify(roleRepository, times(1)).existsById(roleId);
        verify(roleRepository, times(1)).deleteById(roleId);
    }

    @DisplayName("JUnit test for deleteRoleById method throws ResourceNotFoundException")
    @Test
    void deleteRoleById_NotExistingId_ShouldThrowResourceNotFoundException() {
        var roleId = UUID.randomUUID();

        when(roleRepository.existsById(roleId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> roleService.deleteRoleById(roleId));
        verify(roleRepository, times(1)).existsById(roleId);
        verify(roleRepository, never()).findById(roleId);
    }
}

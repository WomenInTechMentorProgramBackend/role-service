package com.medicalcenter.roleservice.service;

import com.medicalcenter.roleservice.entity.Permission;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.PermissionRepository;
import com.medicalcenter.roleservice.service.impl.PermissionServiceImpl;
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
public class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    private Permission permission;

    @BeforeEach
    public void setUp() {
        permission = Permission.builder()
                .id(UUID.randomUUID())
                .name("Permission 1")
                .description("Description 1")
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 1")
                .build();
    }

    @DisplayName("JUnit test for getAllPermissions method")
    @Test
    void getAllPermissions_ShouldReturnListOfPermissions() {
        var permission1 = Permission.builder()
                .id(UUID.randomUUID())
                .name("Permission 2")
                .description("Description 2")
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 2")
                .build();

        when(permissionRepository.findAll()).thenReturn(List.of(permission, permission1));

        var permissionList = permissionService.getAllPermissions();

        assertEquals(2, permissionList.size());
        verify(permissionRepository, times(1)).findAll();
    }

    @DisplayName("JUnit test for getPermissionById method")
    @Test
    void getPermissionById_ExistingId_ShouldReturnPermissionObject() {
        var permissionId = permission.getId();
        when(permissionRepository.existsById(permissionId)).thenReturn(true);
        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));

        var result = permissionService.getPermissionById(permissionId);

        assertTrue(result.isPresent());
        assertEquals(permission, result.get());
        verify(permissionRepository, times(1)).existsById(permissionId);
        verify(permissionRepository, times(1)).findById(permissionId);
    }

    @DisplayName("JUnit test for getPermissionById method throws ResourceNotFoundException")
    @Test
    void getPermissionById_NonExistingId_ShouldThrowResourceNotFoundException() {
        var permissionId = UUID.randomUUID();

        when(permissionRepository.existsById(permissionId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> permissionService.getPermissionById(permissionId));
        verify(permissionRepository, times(1)).existsById(permissionId);
        verify(permissionRepository, never()).findById(permissionId);
    }

    @DisplayName("JUnit test for savePermission method")
    @Test
    void savePermission_NewPermission_ShouldReturnPermissionObject() {
        when(permissionRepository.findByName(permission.getName())).thenReturn(Optional.empty());

        when(permissionRepository.save(permission)).thenReturn(permission);

        var result = permissionService.savePermission(permission);

        assertNotNull(result);
        assertEquals(permission, result);

        verify(permissionRepository, times(1)).findByName(permission.getName());
        verify(permissionRepository, times(1)).save(permission);
    }

    @DisplayName("JUnit test for savePermission method which throws ObjectAlreadyExistException")
    @Test
    void savePermission_ExistingName_ShouldThrowObjectAlreadyExistException() {
        when(permissionRepository.findByName(permission.getName())).thenReturn(Optional.of(permission));

        assertThrows(ObjectAlreadyExistException.class, () -> permissionService.savePermission(permission));

        verify(permissionRepository, times(1)).findByName(permission.getName());
        verify(permissionRepository, never()).save(permission);
    }

    @DisplayName("JUnit test for updatePermission method")
    @Test
    void updatePermission_ExistingId_ShouldReturnUpdatedPermission() {
        var permissionId = permission.getId();

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.of(permission));
        when(permissionRepository.save(permission)).thenReturn(permission);

        permission.setName("Updated Permission");
        permission.setDescription("Updated Description");
        permission.setActive(false);
        permission.setCreatedAt(LocalDateTime.now());
        permission.setCreatedBy("Updated User");

        var updatedPermission = permissionService.updatePermission(permissionId, permission);

        assertNotNull(updatedPermission);
        assertEquals(permission.getName(), updatedPermission.getName());
        assertEquals(permission.getDescription(), updatedPermission.getDescription());
        assertEquals(permission.isActive(), updatedPermission.isActive());
        verify(permissionRepository, times(1)).findById(permissionId);
        verify(permissionRepository, times(1)).save(permission);
    }

    @DisplayName("JUnit test for updatePermission method throws ResourceNotFoundException")
    @Test
    void updatePermission_NotExistingId_ShouldThrowResourceNotFoundException() {
        var permissionId = UUID.randomUUID();
        var updatedPermission = new Permission();
        updatedPermission.setName("Updated Permission");
        updatedPermission.setDescription("Updated Description");
        updatedPermission.setActive(false);
        updatedPermission.setCreatedAt(LocalDateTime.now());
        updatedPermission.setCreatedBy("Updated User");

        when(permissionRepository.findById(permissionId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> permissionService.updatePermission(permissionId, updatedPermission));
        verify(permissionRepository, times(1)).findById(permissionId);
        verify(permissionRepository, never()).save(any(Permission.class));
    }

    @DisplayName("JUnit test for deletePermissionById method")
    @Test
    void deletePermissionById_ExistingId_ShouldReturnDeletedMessage() {
        var permissionId = permission.getId();
        when(permissionRepository.existsById(permissionId)).thenReturn(true);

        var result = permissionService.deletePermissionById(permissionId);

        assertNotNull(result);
        assertEquals("Permission(" + permissionId + ")" +  " has been deleted!", result);
        verify(permissionRepository, times(1)).existsById(permissionId);
        verify(permissionRepository, times(1)).deleteById(permissionId);
    }

    @DisplayName("JUnit test for deletePermissionById method throws ResourceNotFoundException")
    @Test
    void deletePermissionById_NotExistingId_ShouldThrowResourceNotFoundException() {
        var permissionId = UUID.randomUUID();

        when(permissionRepository.existsById(permissionId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> permissionService.deletePermissionById(permissionId));
        verify(permissionRepository, times(1)).existsById(permissionId);
        verify(permissionRepository, never()).findById(permissionId);
    }
}

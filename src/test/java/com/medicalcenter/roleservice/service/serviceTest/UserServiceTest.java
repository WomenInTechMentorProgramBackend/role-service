package com.medicalcenter.roleservice.service.serviceTest;

import com.medicalcenter.roleservice.entity.User;
import com.medicalcenter.roleservice.exception.ObjectAlreadyExistException;
import com.medicalcenter.roleservice.exception.ResourceNotFoundException;
import com.medicalcenter.roleservice.repository.UserRepository;
import com.medicalcenter.roleservice.service.impl.UserServiceImpl;
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
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(UUID.randomUUID())
                .externalId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 1")
                .build();
    }

    @DisplayName("JUnit test for getAllUsers method")
    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        var user1 = User.builder()
                .id(UUID.randomUUID())
                .externalId(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"))
                .createdAt(LocalDateTime.now())
                .createdBy("Test User 2")
                .build();

        when(userRepository.findAll()).thenReturn(List.of(user, user1));

        var userList = userService.getAllUsers();

        assertEquals(2, userList.size());
        verify(userRepository, times(1)).findAll();
    }

    @DisplayName("JUnit test for getUserById method")
    @Test
    void getUserById_ExistingId_ShouldReturnRoleObject() {
        var userId = UUID.randomUUID();

        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        var result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).findById(userId);
    }

    @DisplayName("JUnit test for getUserById method throws ResourceNotFoundException")
    @Test
    void getUserById_NotExistingId_ShouldThrowResourceNotFoundException() {
        var userId = UUID.randomUUID();

        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, never()).findById(userId);
    }

    @DisplayName("JUnit test for saveUser method")
    @Test
    void saveUser_NewUser_ShouldReturnUserObject() {
        when(userRepository.findByExternalId(user.getExternalId())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        var result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findByExternalId(user.getExternalId());
        verify(userRepository, times(1)).save(user);
    }

    @DisplayName("JUnit test for saveUser method throws ObjectAlreadyExistException")
    @Test
    void saveUser_ExistingExternalId_ShouldThrowObjectAlreadyExistException() {
        when(userRepository.findByExternalId(user.getExternalId())).thenReturn(Optional.of(user));

        assertThrows(ObjectAlreadyExistException.class, () -> userService.saveUser(user));

        verify(userRepository, times(1)).findByExternalId(user.getExternalId());
        verify(userRepository, never()).save(user);
    }

    @DisplayName("JUnit test for updateUser method")
    @Test
    void updateUser_ExistingId_ShouldReturnUpdatedUser() {
        var userId = user.getId();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        user.setExternalId(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"));
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("Test User 2");

        var updatedUser = userService.updateUser(userId, user);

        assertNotNull(updatedUser);
        assertEquals(user.getExternalId(), updatedUser.getExternalId());
        assertEquals(user.getCreatedAt(), updatedUser.getCreatedAt());
        assertEquals(user.getCreatedBy(), updatedUser.getCreatedBy());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }

    @DisplayName("JUnit test for updateUser method throws ResourceNotFoundException")
    @Test
    void updateUser_NotExistingId_ShouldThrowResourceNotFoundException() {
        var userId = UUID.randomUUID();
        var updatedUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userId, updatedUser));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(any(User.class));
    }

    @DisplayName("JUnit test for deleteUserById method")
    @Test
    void deleteUserById_ExistingId_ShouldReturnDeleteMessage() {
        var userId = user.getId();

        when(userRepository.existsById(userId)).thenReturn(true);

        var result = userService.deleteUserByID(userId);

        assertNotNull(result);
        assertEquals("User(" + userId + ")" + " has been deleted!", result);
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @DisplayName("JUnit test for deleteUserById method throws ResourceNotFoundException")
    @Test
    void deleteUserById_NotExistingId_ShouldThrowResourceNotFoundException() {
        var userId = UUID.randomUUID();

        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUserByID(userId));
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, never()).findById(userId);
    }
}

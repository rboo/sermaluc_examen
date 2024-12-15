package org.example.examen.application.service;

import org.example.examen.application.mapper.PhoneDTOMapper;
import org.example.examen.application.port.output.PhonePersistencePort;
import org.example.examen.application.port.output.UserPersistencePort;
import org.example.examen.common.exception.CustomerDuplicationViolationException;
import org.example.examen.domain.model.Phone;
import org.example.examen.domain.model.User;
import org.example.examen.domain.model.dto.PhoneDTO;
import org.example.examen.domain.model.dto.UserDTO;
import org.example.examen.domain.service.UserService;
import org.example.examen.infraestructure.utils.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private PhonePersistencePort phonePersistencePort;

    @Mock
    private PhoneDTOMapper phoneDTOMapper;

    @InjectMocks
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_SuccessfulCreationWithoutPhones() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPhones(Collections.emptyList());

        User createdUser = new User();
        createdUser.setId(1L);
        createdUser.setCreated(LocalDate.now());
        createdUser.setModified(LocalDate.now());
        createdUser.setLastLogin(LocalDate.now());
        createdUser.setToken(UUID.randomUUID().toString());
        createdUser.setActive(true);

        when(userPersistencePort.existsUserByEmail(user.getEmail())).thenReturn(false);
        when(userPersistencePort.create(user)).thenReturn(createdUser);

        UserDTO result = userService.createUser(user);

        assertEquals(createdUser.getCreated(), result.getCreated());
        assertEquals(createdUser.getModified(), result.getModified());
        assertEquals(createdUser.getLastLogin(), result.getLastLogin());
        assertEquals(createdUser.isActive(), result.isActive());
        verify(phonePersistencePort, never()).create(any());
    }

    @Test
    void testCreateUser_SuccessfulCreationWithPhones() {
        User user = new User();
        user.setEmail("test@example.com");
        PhoneDTO phone = new PhoneDTO();
        user.setPhones(List.of(phone));

        User createdUser = new User();
        createdUser.setId(1L);
        createdUser.setCreated(LocalDate.now());
        createdUser.setModified(LocalDate.now());
        createdUser.setLastLogin(LocalDate.now());
        createdUser.setToken(UUID.randomUUID().toString());
        createdUser.setActive(true);

        Phone phoneModel = new Phone();
        phoneModel.setUserId(createdUser.getId());

        when(userPersistencePort.existsUserByEmail(user.getEmail())).thenReturn(false);
        when(userPersistencePort.create(user)).thenReturn(createdUser);
        when(phoneDTOMapper.toModel(phone)).thenReturn(phoneModel);

        UserDTO result = userService.createUser(user);

        assertEquals(createdUser.getCreated(), result.getCreated());
        assertEquals(createdUser.getModified(), result.getModified());
        assertEquals(createdUser.getLastLogin(), result.getLastLogin());
        assertEquals(createdUser.isActive(), result.isActive());
        verify(phonePersistencePort, times(1)).create(phoneModel);
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        User user = new User();
        user.setEmail("duplicate@example.com");

        when(userPersistencePort.existsUserByEmail(user.getEmail())).thenReturn(true);

        assertThrows(CustomerDuplicationViolationException.class, () -> userService.createUser(user));
        verify(userPersistencePort, never()).create(any());
        verify(phonePersistencePort, never()).create(any());
    }
}
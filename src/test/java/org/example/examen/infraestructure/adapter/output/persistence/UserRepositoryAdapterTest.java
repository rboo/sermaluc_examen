package org.example.examen.infraestructure.adapter.output.persistence;

import org.example.examen.domain.model.User;
import org.example.examen.infraestructure.adapter.output.persistence.entity.UserEntity;
import org.example.examen.infraestructure.adapter.output.persistence.mapper.UserDboMapper;
import org.example.examen.infraestructure.adapter.output.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserRepositoryAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDboMapper userDboMapper;

    @InjectMocks
    private UserRepositoryAdapter userRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        UserEntity userEntity = new UserEntity();
        UserEntity savedUserEntity = new UserEntity();

        savedUserEntity.setId(1L);
        savedUserEntity.setEmail("test@example.com");
        savedUserEntity.setActive(true);
        savedUserEntity.setToken(UUID.randomUUID().toString());
        savedUserEntity.setCreated(LocalDate.now());
        savedUserEntity.setLastLogin(LocalDate.now());

        when(userDboMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(savedUserEntity);
        when(userDboMapper.toDomain(savedUserEntity)).thenReturn(user);

        User createdUser = userRepositoryAdapter.create(user);

        assertEquals(user, createdUser);
        assertTrue(savedUserEntity.isActive());
        assertEquals(savedUserEntity.getCreated(), LocalDate.now());
        assertEquals(savedUserEntity.getLastLogin(), LocalDate.now());
    }

    @Test
    void testExistsUserByEmail() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new UserEntity()));
        boolean exists = userRepositoryAdapter.existsUserByEmail(email);
        assertTrue(exists);
    }
}
package org.example.examen.infraestructure.adapter.output.persistence;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.examen.domain.model.User;
import org.example.examen.application.port.output.UserPersistencePort;
import org.example.examen.infraestructure.adapter.output.persistence.mapper.UserDboMapper;
import org.example.examen.infraestructure.adapter.output.persistence.entity.UserEntity;
import org.example.examen.infraestructure.adapter.output.persistence.repository.UserRepository;

import java.time.LocalDate;
import java.util.UUID;


@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserDboMapper userDboMapper;

    @Override
    @Transactional
    public User create(User user) {
        UserEntity userEntity = this.userDboMapper.toEntity(user);
        userEntity.setActive(Boolean.TRUE);
        userEntity.setToken(UUID.randomUUID().toString());
        userEntity.setCreated(LocalDate.now());
        userEntity.setLastLogin(LocalDate.now());
        userEntity = this.userRepository.save(userEntity);
        return this.userDboMapper.toDomain(userEntity);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }
}

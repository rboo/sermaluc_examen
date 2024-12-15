package org.example.examen.infraestructure.adapter.config;

import org.example.examen.application.mapper.PhoneDTOMapper;
import org.example.examen.application.port.output.PhonePersistencePort;
import org.example.examen.domain.service.UserService;
import org.example.examen.infraestructure.adapter.output.persistence.PhoneRepositoryAdapter;
import org.example.examen.infraestructure.adapter.output.persistence.UserRepositoryAdapter;
import org.example.examen.infraestructure.adapter.output.persistence.mapper.PhoneDboMapper;
import org.example.examen.infraestructure.adapter.output.persistence.mapper.UserDboMapper;
import org.example.examen.infraestructure.adapter.output.persistence.repository.PhoneRepository;
import org.example.examen.infraestructure.adapter.output.persistence.repository.UserRepository;
import org.example.examen.infraestructure.utils.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserRepositoryAdapter userRepositoryAdapter(final UserRepository userRepository, final UserDboMapper userDboMapper) {
        return new UserRepositoryAdapter(userRepository, userDboMapper);
    }

    @Bean
    public PhoneRepositoryAdapter phoneRepositoryAdapter(final PhoneRepository phoneRepository, final PhoneDboMapper phoneDboMapper) {
        return new PhoneRepositoryAdapter(phoneRepository, phoneDboMapper);
    }

    @Bean
    public UserService userService(final UserRepositoryAdapter userRepositoryAdapter, final PhonePersistencePort phonePersistencePort, final PhoneDTOMapper phoneDTOMapper, final JwtService jwtService) {
        return new UserService(userRepositoryAdapter, phonePersistencePort, phoneDTOMapper, jwtService);
    }
}

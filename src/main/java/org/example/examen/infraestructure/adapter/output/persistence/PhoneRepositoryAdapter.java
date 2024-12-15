package org.example.examen.infraestructure.adapter.output.persistence;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.examen.domain.model.Phone;
import org.example.examen.application.port.output.PhonePersistencePort;
import org.example.examen.infraestructure.adapter.output.persistence.mapper.PhoneDboMapper;
import org.example.examen.infraestructure.adapter.output.persistence.entity.PhoneEntity;
import org.example.examen.infraestructure.adapter.output.persistence.repository.PhoneRepository;

@RequiredArgsConstructor
public class PhoneRepositoryAdapter implements PhonePersistencePort {

    private final PhoneRepository phoneRepository;
    private final PhoneDboMapper phoneDboMapper;

    @Override
    @Transactional
    public Phone create(Phone phone) {
        PhoneEntity phoneEntity = this.phoneDboMapper.toEntity(phone);
        phoneEntity = this.phoneRepository.save(phoneEntity);
        return this.phoneDboMapper.toDomain(phoneEntity);
    }
}

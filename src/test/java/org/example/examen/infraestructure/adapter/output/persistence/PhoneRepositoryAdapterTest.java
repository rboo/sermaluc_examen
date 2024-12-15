package org.example.examen.infraestructure.adapter.output.persistence;

import org.example.examen.domain.model.Phone;
import org.example.examen.infraestructure.adapter.output.persistence.entity.PhoneEntity;
import org.example.examen.infraestructure.adapter.output.persistence.mapper.PhoneDboMapper;
import org.example.examen.infraestructure.adapter.output.persistence.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PhoneRepositoryAdapterTest {

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private PhoneDboMapper phoneDboMapper;

    @InjectMocks
    private PhoneRepositoryAdapter phoneRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePhone() {
        Phone phone = new Phone();
        PhoneEntity phoneEntity = new PhoneEntity();
        PhoneEntity savedPhoneEntity = new PhoneEntity();

        when(phoneDboMapper.toEntity(phone)).thenReturn(phoneEntity);
        when(phoneRepository.save(phoneEntity)).thenReturn(savedPhoneEntity);
        when(phoneDboMapper.toDomain(savedPhoneEntity)).thenReturn(phone);

        Phone createdPhone = phoneRepositoryAdapter.create(phone);

        assertEquals(phone, createdPhone);
    }
}
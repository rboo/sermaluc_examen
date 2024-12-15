package org.example.examen.application.port.output;

import org.example.examen.domain.model.Phone;

public interface PhonePersistencePort {

    Phone create(Phone phone);
}

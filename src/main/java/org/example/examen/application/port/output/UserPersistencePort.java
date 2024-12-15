package org.example.examen.application.port.output;

import org.example.examen.domain.model.User;

public interface UserPersistencePort {

    User create(User user);

    boolean existsUserByEmail(String email);
}

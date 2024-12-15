package org.example.examen.application.port.input;

import org.example.examen.domain.model.User;
import org.example.examen.domain.model.dto.UserDTO;

public interface UserUseCase {
    UserDTO createUser(User user);
}

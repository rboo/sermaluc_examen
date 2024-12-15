package org.example.examen.infraestructure.adapter.input.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.examen.application.port.input.UserUseCase;
import org.example.examen.domain.model.User;
import org.example.examen.domain.model.dto.UserDTO;
import org.example.examen.infraestructure.adapter.input.rest.data.request.UserCreateRequest;
import org.example.examen.infraestructure.adapter.input.rest.mapper.UserRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestAdapter {

    private final UserUseCase userUseCase;

    private final UserRestMapper userRestMapper;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreateRequest userRquest) {

        User user = this.userRestMapper.toUser(userRquest);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.userUseCase.createUser(user));
    }
}

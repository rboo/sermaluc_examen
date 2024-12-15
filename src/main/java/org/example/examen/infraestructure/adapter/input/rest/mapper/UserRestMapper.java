package org.example.examen.infraestructure.adapter.input.rest.mapper;

import org.example.examen.domain.model.User;
import org.example.examen.infraestructure.adapter.input.rest.data.request.UserCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRestMapper {

    User toUser(UserCreateRequest userCreateRequest);

}

package org.example.examen.infraestructure.adapter.output.persistence.mapper;

import org.example.examen.domain.model.User;
import org.example.examen.infraestructure.adapter.output.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDboMapper {
    /*@Mapping(source="id", target="id")
    @Mapping(source = "name", target = "name")
    @Mapping(source="email", target="email")
    @Mapping(source = "password", target = "password")
    @Mapping(source="created", target="created")
    @Mapping(source="modified", target="modified")
    @Mapping(source = "lastLogin", target = "lastLogin")
    @Mapping(source="token", target="token")
    @Mapping(source="active", target="active")*/
    User toDomain(UserEntity model);
    //@InheritInverseConfiguration
    UserEntity toEntity(User entity);
}

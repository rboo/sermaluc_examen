package org.example.examen.application.mapper;

import org.example.examen.domain.model.Phone;
import org.example.examen.domain.model.dto.PhoneDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneDTOMapper {

    Phone toModel(PhoneDTO phoneDTO);
}

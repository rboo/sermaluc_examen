package org.example.examen.infraestructure.adapter.output.persistence.mapper;

import org.example.examen.domain.model.Phone;
import org.example.examen.infraestructure.adapter.output.persistence.entity.PhoneEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface PhoneDboMapper {
    /*@Mapping(source="id", target="id")
    @Mapping(source="number", target="number")
    @Mapping(source = "cityCode", target = "cityCode")
    @Mapping(source = "countryCode", target = "countryCode")*/
    Phone toDomain(PhoneEntity model);
    //@InheritInverseConfiguration
    PhoneEntity toEntity(Phone entity);
}

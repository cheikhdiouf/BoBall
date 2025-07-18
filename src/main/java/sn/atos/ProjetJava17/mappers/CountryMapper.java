package sn.atos.ProjetJava17.mappers;

import org.mapstruct.Mapper;
import sn.atos.ProjetJava17.dto.CountryDto;
import sn.atos.ProjetJava17.entites.CountryEntity;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryEntity toEntity(CountryDto countryDto);
    CountryDto fromEntity(CountryEntity countryEntity);
}

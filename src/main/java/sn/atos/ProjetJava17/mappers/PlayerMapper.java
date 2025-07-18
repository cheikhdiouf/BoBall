package sn.atos.ProjetJava17.mappers;

import org.mapstruct.Mapper;
import sn.atos.ProjetJava17.dto.CountryDto;
import sn.atos.ProjetJava17.dto.PlayerDto;
import sn.atos.ProjetJava17.entites.CountryEntity;
import sn.atos.ProjetJava17.entites.PlayerEntity;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerEntity toEntity(PlayerDto playerDto);
    PlayerDto fromEntity(PlayerEntity playerEntity);
}

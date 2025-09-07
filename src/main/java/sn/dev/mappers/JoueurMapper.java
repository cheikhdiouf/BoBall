package sn.dev.mappers;

import org.mapstruct.Mapper;
import sn.dev.dto.JoueurDto;
import sn.dev.entites.Joueur;


@Mapper(componentModel = "spring")
public interface JoueurMapper extends  GlobalMapper<JoueurDto, Joueur>{
}

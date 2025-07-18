package sn.atos.ProjetJava17.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.atos.ProjetJava17.dto.JoueurDto;
import sn.atos.ProjetJava17.entites.Joueur;


@Mapper(componentModel = "spring")
public interface JoueurMapper extends  GlobalMapper<JoueurDto, Joueur>{
}

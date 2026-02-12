package sn.atos.ProjetJava17.mappers;

import org.mapstruct.Mapper;
import sn.atos.ProjetJava17.dto.DemandeDevisDto;
import sn.atos.ProjetJava17.entites.DemandeDevis;


@Mapper(componentModel = "spring")
public interface DemandeDevisMapper extends  GlobalMapper<DemandeDevisDto, DemandeDevis>{
}

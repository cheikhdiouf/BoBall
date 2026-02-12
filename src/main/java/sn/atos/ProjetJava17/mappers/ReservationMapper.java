package sn.atos.ProjetJava17.mappers;

import org.mapstruct.Mapper;
import sn.atos.ProjetJava17.dto.ReservationDto;
import sn.atos.ProjetJava17.entites.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper
        extends GlobalMapper<ReservationDto, Reservation> {
}
